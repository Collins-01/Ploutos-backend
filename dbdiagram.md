//////////////////////////////////////////////////////
// USERS & PROFILES
//////////////////////////////////////////////////////

Table users {
  user_id uuid [pk]
  email text [unique, not null]
  auth_provider text [not null]
  password_hash text
  created_at timestamp [default: `now()`]
}

Table profiles {
  profile_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  display_name text
  timezone text
  locale text
  metadata jsonb
  updated_at timestamp [default: `now()`]
}

//////////////////////////////////////////////////////
// CATEGORIES
//////////////////////////////////////////////////////

Table categories {
  category_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  name text [not null]
  parent_id uuid [ref: > categories.category_id]
  is_system boolean [default: false]
  created_at timestamp [default: `now()`]
}

//////////////////////////////////////////////////////
// MULTI-CURRENCY SUPPORT
//////////////////////////////////////////////////////

Table currencies {
  code char(3) [pk] // USD, NGN, EUR
  name text
  symbol text
}

Table fx_rates {
  rate_id bigserial [pk]
  base_currency char(3) [not null, ref: > currencies.code]
  quote_currency char(3) [not null, ref: > currencies.code]
  rate numeric(20,8) [not null]
  source text
  timestamp timestamp [not null, default: `now()`]
}

//////////////////////////////////////////////////////
// ACCOUNTS
//////////////////////////////////////////////////////

Table accounts {
  account_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  account_type text [not null]
  currency char(3) [not null, ref: > currencies.code]
  created_at timestamp [default: `now()`]
  metadata jsonb
}

//////////////////////////////////////////////////////
// TRANSACTIONS
//////////////////////////////////////////////////////

Table transactions {
  transaction_id uuid [pk]
  account_id uuid [not null, ref: > accounts.account_id]
  category_id uuid [ref: > categories.category_id]
  amount numeric(20,4) [not null]
  currency char(3) [not null, ref: > currencies.code]
  direction text [not null] // debit | credit
  description text
  occurred_at timestamp [not null, default: `now()`]
  created_at timestamp [default: `now()`]
  metadata jsonb
}

//////////////////////////////////////////////////////
// MONTHLY BUDGETS
//////////////////////////////////////////////////////

Table budgets {
  budget_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  month date [not null]
  name text [not null]
  created_at timestamp [default: `now()`]
}

Table budget_allocations {
  allocation_id uuid [pk]
  budget_id uuid [not null, ref: > budgets.budget_id]
  category_id uuid [not null, ref: > categories.category_id]
  amount numeric(20,4) [not null]
  created_at timestamp [default: `now()`]
}

//////////////////////////////////////////////////////
// RECURRING BUDGETS
//////////////////////////////////////////////////////

Table recurring_budgets {
  recurring_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  name text [not null]
  frequency text [not null] // monthly | weekly | quarterly | yearly
  start_date date [not null]
  end_date date
  created_at timestamp [default: `now()`]
}

Table recurring_budget_rules {
  rule_id uuid [pk]
  recurring_id uuid [not null, ref: > recurring_budgets.recurring_id]
  category_id uuid [not null, ref: > categories.category_id]
  amount numeric(20,4)
  created_at timestamp [default: `now()`]
}

//////////////////////////////////////////////////////
// FORMULA-BASED BUDGET RULES
//////////////////////////////////////////////////////

Table formula_rules {
  formula_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  category_id uuid [not null, ref: > categories.category_id]
  expression text [not null] 
  metadata jsonb
  created_at timestamp [default: `now()`]
}

//////////////////////////////////////////////////////
// ENVELOPES (Zero-Based Budgeting)
//////////////////////////////////////////////////////

Table envelopes {
  envelope_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  name text [not null]
  category_id uuid [ref: > categories.category_id]
  archived boolean [default: false]
  created_at timestamp [default: `now()`]
}

Table envelope_funding {
  funding_id uuid [pk]
  envelope_id uuid [not null, ref: > envelopes.envelope_id]
  amount numeric(20,4) [not null]
  month date [not null]
  created_at timestamp [default: `now()`]
}

Table envelope_activity {
  activity_id uuid [pk]
  envelope_id uuid [not null, ref: > envelopes.envelope_id]
  transaction_id uuid [not null, ref: > transactions.transaction_id]
  amount numeric(20,4) [not null]
  created_at timestamp [default: `now()`]
}

//////////////////////////////////////////////////////
// BILL REMINDERS
//////////////////////////////////////////////////////

Table bill_reminders {
  reminder_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  name text [not null]
  amount numeric(20,4)
  currency char(3) [ref: > currencies.code]
  frequency text [not null]
  next_due date [not null]
  category_id uuid [ref: > categories.category_id]
  auto_pay boolean [default: false]
  metadata jsonb
  created_at timestamp [default: `now()`]
}

Table bill_payments {
  bill_payment_id uuid [pk]
  reminder_id uuid [not null, ref: > bill_reminders.reminder_id]
  transaction_id uuid [ref: > transactions.transaction_id]
  amount numeric(20,4)
  paid_at timestamp
  status text
  created_at timestamp [default: `now()`]
}

//////////////////////////////////////////////////////
// SAVINGS GOALS
//////////////////////////////////////////////////////

Table savings_goals {
  goal_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  name text [not null]
  target_amount numeric(20,4) [not null]
  current_amount numeric(20,4) [default: 0]
  deadline date
  category_id uuid [ref: > categories.category_id]
  created_at timestamp [default: `now()`]
  metadata jsonb
}

Table savings_goal_funding {
  funding_id uuid [pk]
  goal_id uuid [not null, ref: > savings_goals.goal_id]
  amount numeric(20,4) [not null]
  transaction_id uuid [ref: > transactions.transaction_id]
  created_at timestamp [default: `now()`]
}

//////////////////////////////////////////////////////
// INCOME TABLES
//////////////////////////////////////////////////////

Table income_sources {
  source_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  name text [not null]
  description text
  created_at timestamp [default: `now()`]
}

Table income_payments {
  income_id uuid [pk]
  source_id uuid [not null, ref: > income_sources.source_id]
  amount numeric(20,4) [not null]
  currency char(3) [ref: > currencies.code]
  received_at timestamp [not null, default: `now()`]
  transaction_id uuid [ref: > transactions.transaction_id]
  metadata jsonb
  created_at timestamp [default: `now()`]
}

//////////////////////////////////////////////////////
// BUDGET ROLLOVERS
//////////////////////////////////////////////////////

Table budget_rollovers {
  rollover_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  category_id uuid [not null, ref: > categories.category_id]
  from_month date [not null]
  to_month date [not null]
  amount numeric(20,4) [not null]
  created_at timestamp [default: `now()`]
}

Table budget_closings {
  closing_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  month date [not null]
  closed_at timestamp [default: `now()`]
  notes text
}

//////////////////////////////////////////////////////
// LOANS & PAYMENTS
//////////////////////////////////////////////////////

Table loans {
  loan_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  principal numeric(20,4) [not null]
  outstanding_balance numeric(20,4) [not null]
  interest_rate numeric(6,4) [not null]
  status text [not null]
  terms jsonb
  created_at timestamp [default: `now()`]
}

Table payments {
  payment_id uuid [pk]
  loan_id uuid [not null, ref: > loans.loan_id]
  amount numeric(20,4) [not null]
  scheduled_at timestamp [not null]
  paid_at timestamp
  status text [not null]
  metadata jsonb
}

//////////////////////////////////////////////////////
// CONTACTS
//////////////////////////////////////////////////////

Table contacts {
  contact_id uuid [pk]
  user_id uuid [not null, ref: > users.user_id]
  name text [not null]
  phone text
  email text
  created_at timestamp [default: `now()`]
  metadata jsonb
}

//////////////////////////////////////////////////////
// EVENT STORE
//////////////////////////////////////////////////////

Table ledger_events {
  event_id bigserial [pk]
  aggregate_type text [not null]
  aggregate_id uuid [not null]
  event_type text [not null]
  payload jsonb [not null]
  created_at timestamp [default: `now()`]
}


Ref: "budget_rollovers"."user_id" < "budget_rollovers"."to_month"