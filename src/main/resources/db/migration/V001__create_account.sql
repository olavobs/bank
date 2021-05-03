create table `account` (
    `id` bigint(20) auto_increment primary key,
    `identifier` varchar(50) unique,
    `name` varchar(50),
    `description` varchar(200),
    `amount` decimal(10,2),
    `status` varchar(20) not null,
    `created_date` datetime not null
);

create table `account_history` (
    `id` bigint(20) auto_increment primary key,
    `source_account_id` bigint(20) ,
    `transaction_date` datetime not null,
    `transaction_amount` decimal(10,2) not null,
    `transaction_type` varchar(20) not null,
    `description` varchar(100),
    constraint `fk_account_transaction_source_account_id` foreign key (`source_account_id`) references `account` (`id`)
);