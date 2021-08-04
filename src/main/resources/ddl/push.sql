create table DOM_ISS.T_ISS_ACCT
                    (
                       ISS_ACCT_ID          int     AUTO_INCREMENT             not null,
                       USR_NM               VARCHAR(255)         not null,
                       PWD                  VARCHAR(255)         not null,
                       TPH_NUM              VARCHAR(10)          not null,
                       EMAIL                VARCHAR(20)          not null,
                       CNTRY                VARCHAR(20)          not null,
                       INS_TS               TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null,
                       UPD_TS               TIMESTAMP,
                       constraint PK_IA primary key (ISS_ACCT_ID)
                    );