----------------------------------------------------------------
------------ < 실습 1 > 사용자 생성 ------------ 
----------------------------------------------------------------
/* ########  ID : SYSTEM ######## */

ALTER session set "_ORACLE_SCRIPT"=true;
DROP USER suser CASCADE; -- 기존 사용자 삭제(현재 접속되어 있으면 삭제 안 됨)
	-- CASCADE option : 관련 스키마 개체들도 함께 삭제.  Default는 No Action
CREATE USER suser IDENTIFIED BY 1234  -- 사용자 ID : suser, 비밀번호 : 1234
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP;
GRANT connect, resource, dba TO suser; -- 권한 부여

-----------------------------------------------
----------------- 테이블 생성 ------------------
-----------------------------------------------
/*############ ID : SUSER #############*/

CREATE TABLE 유저 (
    유저_아이디 NUMBER(10) PRIMARY KEY,  --학번
    유저_비밀번호 VARCHAR(20),
    관리자_여부 CHAR(1) CHECK(관리자_여부 IN ('0','1')),  --오라클에선 BOOLEAN이 없어 0과 1로 표현
	이름	NCHAR(5) NOT NULL,
	전화번호	CHAR(20),
	학년	NUMBER(1)
);

CREATE TABLE 팀(
    팀_번호 NUMBER(5) PRIMARY KEY,
    팀_이름 VARCHAR(20) UNIQUE,
    관리자_아이디 NUMBER(10),
    FOREIGN KEY (관리자_아이디) REFERENCES 유저(유저_아이디)
);

CREATE TABLE 소속(
    팀_번호 NUMBER(5),
    유저_아이디 NUMBER(10),
    PRIMARY KEY (팀_번호, 유저_아이디),
    FOREIGN KEY (팀_번호) REFERENCES 팀(팀_번호),
	FOREIGN KEY (유저_아이디) REFERENCES 유저(유저_아이디)
);

CREATE TABLE 스케줄(
    스케줄_번호 NUMBER(5) PRIMARY KEY,
    유저_아이디 NUMBER(10),
    스케줄_이름 VARCHAR(20),
    요일 VARCHAR(5),
    시작시간 NUMBER(3),
    종료시간 NUMBER(3),
    고정여부 CHAR(1) CHECK(고정여부 IN ('0','1')),
    날짜 DATE,
    메모 VARCHAR(50),
    FOREIGN KEY (유저_아이디) REFERENCES 유저(유저_아이디)
);

CREATE TABLE 통합스케줄(
    통합_번호 NUMBER(5) PRIMARY KEY,
    팀_번호 NUMBER(5),
    통합스케줄_이름 VARCHAR(20),
    요일 VARCHAR(5),
    시작시간 NUMBER(3),
    종료시간 NUMBER(3),
    고정여부 CHAR(1) CHECK(고정여부 IN ('0','1')),
    날짜 DATE,
    메모 VARCHAR(50),
    FOREIGN KEY (팀_번호) REFERENCES 팀(팀_번호)
);

--drop table 통합스케줄 하고 위에 새로 갱신된 테이블 입력하세요.--

-----------------------------------------------
----------------- 데이터 입력 ------------------
-----------------------------------------------

-- 유저(유저_아이디, 유저_비밀번호, 관리자_여부, 이름, 전화번호, 학년)
INSERT INTO 유저 VALUES(20173070, '20173070', 0, '장인혁', '010-0000-0000', 3);
INSERT INTO 유저 VALUES(20203089, '20203089', 1, '정가영', '010-1111-1111', 3);
INSERT INTO 유저 VALUES(20143698, '20143698', 0, '김민호', '010-2352-1257', 5);
INSERT INTO 유저 VALUES(20195150, '20195150', 0, '정유리', '010-8942-4235', 4);
INSERT INTO 유저 VALUES(20129921, '20129921', 0, '장현수', '010-6912-7777', 7);
INSERT INTO 유저 VALUES(20205609, '20205609', 0, '김채원', '010-4491-8956', 3);
INSERT INTO 유저 VALUES(20226289, '20226289', 0, '다나카', '010-4217-5922', 1);
INSERT INTO 유저 VALUES(20210804, '20210804', 0, '현지원', '010-7942-1253', 2);
INSERT INTO 유저 VALUES(20205939, '20205939', 0, '문영헌', '010-8563-1606', 3);
INSERT INTO 유저 VALUES(20196042, '20196042', 1, '박지원', '010-5693-1001', 4);

-- 팀(팀_번호,  팀_이름, 관리자_아이디)
INSERT INTO 팀 VALUES (1, '자바', 20203089);
INSERT INTO 팀 VALUES(2,'디비모임',20196042);
-- 소속(팀_번호, 유저_아이디)
INSERT INTO 소속 VALUES(1, 20203089);
INSERT INTO 소속 VALUES(1, 20173070);

INSERT INTO 소속 VALUES(1, 20143698);
INSERT INTO 소속 VALUES(1, 20195150);
INSERT INTO 소속 VALUES(2, 20129921);
INSERT INTO 소속 VALUES(2, 20205609);
INSERT INTO 소속 VALUES(2, 20226289);
INSERT INTO 소속 VALUES(2, 20210804);
-- 스케줄(스케줄_번호, 유저_아이디, 스케줄_이름, 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모)
INSERT INTO 스케줄 VALUES(1, 20203089, 'DB', '화', 9, 11, 1, NULL, '정보 816');

INSERT INTO 스케줄 VALUES(2, 20203089, '운영체제', '금', 13, 14,1, NULL, '정보 812');
INSERT INTO 스케줄 VALUES(3, 20203089, '웹프로그래밍', '월', 9, 11,1, NULL, '정보 810');
INSERT INTO 스케줄 VALUES(4, 20173070, 'DB', '화', 9, 11, 1, NULL, '정보 816');
INSERT INTO 스케줄 VALUES(5, 20173070, '운영체제', '금', 13, 14,1, NULL, '정보 812');
INSERT INTO 스케줄 VALUES(6, 20173070, '웹프로그래밍', '월', 9, 11,1, NULL, '정보 810');
INSERT INTO 스케줄 VALUES(7, 20143698, '파이썬', '화', 11,13, 1, NULL, '정보 817');
INSERT INTO 스케줄 VALUES(8, 20143698, '마이크로', '금', 14,16,1,NULL, '정보 815');
INSERT INTO 스케줄 VALUES(9, 20143698, '고급캡스톤', '목', 12,15,1,NULL, '정보 815');
INSERT INTO 스케줄 VALUES(10, 20143698, '어셈블리', '수', 10,12,1,NULL, '정보 811');
INSERT INTO 스케줄 VALUES(11, 20195150, '마이크로', '금', 14,16,1,NULL, '정보 815');
INSERT INTO 스케줄 VALUES(12, 20195150, '경제','월', 15,17,1,NULL, '국제관 412');
INSERT INTO 스케줄 VALUES(13, 20195150, '범죄분석', '월',13, 15,1,NULL, '인문2 512');
INSERT INTO 스케줄 VALUES(14, 20195150, '블록체인', '화', 13,15,1,NULL, '정보관 610');

-- 통합스케줄(통합_번호, 팀_번호, 통합스케줄_이름, 요일, 시작시간, 종료시간, 고정여부, 날짜, 메모)
INSERT INTO 통합스케줄 VALUES(1, 1,'회의','금' , 17, 18,'0','2022/11/15', '첫번째 회의');

COMMIT; --커밋 해줘야 됨







