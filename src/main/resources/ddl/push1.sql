
create table DOM_ISS.T_ISS_USR_TPH_TBL_ID
                    (
                        USR_TPH_TBL_ID       INTEGER         AUTO_INCREMENT  not null,
                        TPH_NUM              VARCHAR(255)                    not null,
                        IS_VLD               TINYINT(1)                      not null,       
                        INS_TS               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        UPD_TS               TIMESTAMP,
                        constraint PK_IA primary key (USR_TPH_TBL_ID)
                    );

create table DOM_ISS.T_ISS_USR_EMAIL_TBL_ID
                    (
                        USR_EMAIL_TBL_ID     INTEGER         AUTO_INCREMENT  not null,
                        EMAIL_ID             VARCHAR(255)                    not null,
                        IS_VLD               TINYINT(1)                      not null,       
                        INS_TS               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        UPD_TS               TIMESTAMP,
                        constraint PK_IA primary key (USR_EMAIL_TBL_ID)
                    );

create table DOM_ISS.T_ISS_ACCT_USR_DTL
                    (
                        ACCT_USR_DTL_ID      INTEGER         AUTO_INCREMENT  not null,
                        FRST_NM              VARCHAR(255)                    not null,
                        MDL_NM               VARCHAR(255),
                        LST_NM               VARCHAR(255)                    not null,
                        ADDR_LN_ONE          VARCHAR(255),
                        ADDR_LN_TWO          VARCHAR(255),
                        ZIP_CD               VARCHAR(255)                    not null,
                        CNTRY                VARCHAR(255)                    not null,
                        USR_TPH_TBL_ID       INTEGER                         not null,
                        USR_EMAIL_TBL_ID     INTEGER                         not null,
                        INS_TS               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        UPD_TS               TIMESTAMP,
                        constraint PK_AUD        primary key (ACCT_USR_DTL_ID),
                        constraint FK_UTTI_AUD   foreign key (USR_TPH_TBL_ID) references DOM_ISS.T_ISS_USR_TPH_TBL_ID(USR_TPH_TBL_ID),
                        constraint FK_UETI_AUD   foreign key (USR_EMAIL_TBL_ID) references DOM_ISS.T_ISS_USR_EMAIL_TBL_ID(USR_EMAIL_TBL_ID)
                    );

create table DOM_ISS.T_ISS_ACCT_USR
                    (
                        ISS_ACCT_USR_ID          INTEGER         AUTO_INCREMENT  not null,
                        USR_NM               VARCHAR(255)                    not null,
                        PWD                  VARCHAR(255)                    not null,
                        ACCT_USR_DTL_ID      INTEGER                         not null,       
                        INS_TS               TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        UPD_TS               TIMESTAMP,
                        constraint PK_IA         primary key (ISS_ACCT_USR_ID),
                        constraint FK_AUD_IAU    foreign key (ACCT_USR_DTL_ID) references DOM_ISS.T_ISS_ACCT_USR_DTL(ACCT_USR_DTL_ID)
                    );
SELECT MAX(USR_TPH_TBL_ID) from DOM_ISS.T_ISS_USR_TPH_TBL_ID;
                    
insert into DOM_ISS.T_ISS_ACCT_USR_DTL
                    (
						FRST_NM,
						MDL_NM,
						LST_NM,
						ADDR_LN_ONE,
						ADDR_LN_TWO,
						ZIP_CD,
						CNTRY,
						USR_TPH_TBL_ID,
						USR_EMAIL_TBL_ID) values 
					(
					   "Test", 
					   "N",
					   "TestLastName",
					   "123TestRoad",
					   "TestVille",
					   "21021",
					   "Testistan",
					   );


