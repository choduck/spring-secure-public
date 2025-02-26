CREATE TABLE IF NOT EXISTS normal_account (
    account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    group_code VARCHAR(50),
    financial_institution VARCHAR(100) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    account_alias VARCHAR(100),
    account_type VARCHAR(50) NOT NULL,
    currency_code VARCHAR(10) NOT NULL,
    balance DECIMAL(19, 4) NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    start_date DATE,
    end_date DATE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
); 