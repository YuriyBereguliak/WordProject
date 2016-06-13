USE [Word Project]
go

CREATE TABLE Games
(
	GameID int  NOT NULL ,
	GameNumberOfMoves int  NULL ,
	VocabularyID int  NOT NULL ,
	WinnerUserID int  NULL 
)
go


ALTER TABLE Games
	ADD CONSTRAINT XPKGames PRIMARY KEY  CLUSTERED (GameID ASC)
go


CREATE TABLE Moves
(
	MoveID int  NOT NULL ,
	GameID int  NOT NULL ,
	UserID int  NOT NULL ,
	WordID int  NOT NULL ,
	StepCount int  NOT NULL 
)
go


ALTER TABLE Moves
	ADD CONSTRAINT XPKMoves PRIMARY KEY  CLUSTERED (MoveID ASC)
go


CREATE TABLE Users
(
	UserID int  NOT NULL ,
	UserName nvarchar(20)  NOT NULL ,
	UserNumberOfWins int  NULL ,
	UserGamesCounter int  NULL ,
	UserPerformanceRatio float  NULL 
)
go


ALTER TABLE Users
	ADD CONSTRAINT XPKUsers PRIMARY KEY  CLUSTERED (UserID ASC)
go


CREATE TABLE Users_Games
(
	Users_Games_ID int  NOT NULL ,
	UserID int  NULL ,
	GameID int  NULL 
)
go


ALTER TABLE Users_Games
	ADD CONSTRAINT XPKUsers_Games PRIMARY KEY  CLUSTERED (Users_Games_ID ASC)
go


CREATE TABLE Vocabularies
(
	VocabularyID int  NOT NULL ,
	VocabularyName nvarchar(20)  NULL 
)
go


ALTER TABLE Vocabularies
	ADD CONSTRAINT XPKVocabularies PRIMARY KEY  CLUSTERED (VocabularyID ASC)
go


CREATE TABLE Word_Vocabulary
(
	Word_Vocabulary_ID int  NOT NULL ,
	VocabularyID int  NULL ,
	WordID int  NULL 
)
go


ALTER TABLE Word_Vocabulary
	ADD CONSTRAINT XPKWord_Vocabulary PRIMARY KEY  CLUSTERED (Word_Vocabulary_ID ASC)
go


CREATE TABLE Words
(
	EnglishWord nvarchar(50)  NOT NULL ,
	UkrainianWord nvarchar(50)  NOT NULL ,
	WordID int  NOT NULL ,
	UkrainianLetterStart nvarchar(2)  NULL ,
	UkrainianLetterEnd nvarchar(2)  NULL ,
	EnglishLetterStart nvarchar(2)  NULL ,
	EnglishLetterEnd nvarchar(2)  NULL 
)
go


ALTER TABLE Words
	ADD CONSTRAINT XPKWords PRIMARY KEY  CLUSTERED (WordID ASC)
go



ALTER TABLE Games
	ADD CONSTRAINT  R_76 FOREIGN KEY (VocabularyID) REFERENCES Vocabularies(VocabularyID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE Games
	ADD CONSTRAINT  R_81 FOREIGN KEY (WinnerUserID) REFERENCES Users(UserID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go



ALTER TABLE Moves
	ADD CONSTRAINT  R_73 FOREIGN KEY (WordID) REFERENCES Words(WordID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE Moves
	ADD CONSTRAINT  R_75 FOREIGN KEY (GameID) REFERENCES Games(GameID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE Moves
	ADD CONSTRAINT  R_77 FOREIGN KEY (UserID) REFERENCES Users(UserID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go



ALTER TABLE Users_Games
	ADD CONSTRAINT  R_88 FOREIGN KEY (GameID) REFERENCES Games(GameID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE Users_Games
	ADD CONSTRAINT  R_89 FOREIGN KEY (UserID) REFERENCES Users(UserID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go



ALTER TABLE Word_Vocabulary
	ADD CONSTRAINT  R_90 FOREIGN KEY (VocabularyID) REFERENCES Vocabularies(VocabularyID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

ALTER TABLE Word_Vocabulary
	ADD CONSTRAINT  R_91 FOREIGN KEY (WordID) REFERENCES Words(WordID)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
go

