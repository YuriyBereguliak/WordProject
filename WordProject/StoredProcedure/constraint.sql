use [Word Project]
go

ALTER TABLE Users_Games 
ADD CONSTRAINT constraint1 UNIQUE (UserID, GameID);

ALTER TABLE Word_Vocabulary
ADD CONSTRAINT constraint2 UNIQUE (WordID, VocabularyID);

ALTER TABLE Games
ADD CONSTRAINT constraint3 CHECK (GameNumberOfMoves >= 0);

ALTER TABLE Users
ADD CONSTRAINT constraint4 CHECK (UserNumberOfWins >= 0);

ALTER TABLE Users
ADD CONSTRAINT constraint5 CHECK (UserGamesCounter >= 0);

ALTER TABLE Users
ADD CONSTRAINT constraint6 CHECK (UserPerformanceRatio >= 0 AND UserPerformanceRatio <= 1);

ALTER TABLE Users
ADD CONSTRAINT constraint7 DEFAULT 0 for UserNumberOfWins;

ALTER TABLE Users
ADD CONSTRAINT constraint8 DEFAULT 0 for UserGamesCounter;

ALTER TABLE Users
ADD CONSTRAINT constraint9 DEFAULT 0 for UserPerformanceRatio;

ALTER TABLE Games
ADD CONSTRAINT constraint10 DEFAULT 0 for GameNumberOfMoves;