USE [Word Project]
GO

/****** Object:  StoredProcedure [dbo].[ProcedureAddWord]    Script Date: 24.04.2016 22:38:07 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Yuriy Bereguliak>
-- =============================================
CREATE PROCEDURE [dbo].[ProcedureAddWord]
	@EnWord NVARCHAR(50), 
	@UaWord NVARCHAR(50),
	@Vocabulary NVARCHAR(30) 
AS
DECLARE @count INT
DECLARE @leftLetterUA NVARCHAR(1)
DECLARE @rightLetterUA NVARCHAR(1)	
DECLARE @leftLetterEN NVARCHAR(1)
DECLARE @rightLetterEN NVARCHAR(1)
DECLARE @vocabularyID INT
DECLARE @wordID INT
BEGIN
	SET @count = (SELECT COUNT(EnglishWord) FROM Words WHERE EnglishWord = @EnWord);
	IF(@count = 0)
		BEGIN
			SET @rightLetterUA = (SELECT RIGHT(@UaWord, 1));
			SET @leftLetterUA = (SELECT LEFT (@UaWord, 1));
			SET @rightLetterEN = (SELECT RIGHT(@EnWord, 1));
			SET @leftLetterEN = (SELECT LEFT(@EnWord, 1));
			INSERT INTO Words (EnglishWord, UkrainianWord, UkrainianLetterStart, UkrainianLetterEnd, EnglishLetterStart, EnglishLetterEnd) 
				VALUES (@EnWord, @UaWord, @leftLetterUA, @rightLetterUA, @leftLetterEN, @rightLetterEN);

			SET @wordID = (SELECT WordID FROM Words WHERE EnglishWord = @EnWord);
			SET @vocabularyID = (SELECT VocabularyID FROM Vocabularies WHERE VocabularyName = @Vocabulary);
			INSERT INTO Word_Vocabulary(VocabularyID, WordID)
				VALUES (@vocabularyID, @wordID);
			RETURN 1
		END
	ELSE
		BEGIN
			SET @count = (SELECT COUNT(WordID) FROM Word_Vocabulary
					WHERE WordID = (SELECT WordID FROM Words WHERE EnglishWord = @EnWord)
					AND VocabularyID = (SELECT VocabularyID FROM Vocabularies WHERE VocabularyName = @Vocabulary));
			IF(@count = 0)
				BEGIN
					SET @vocabularyID = (SELECT VocabularyID FROM Vocabularies WHERE VocabularyName = @Vocabulary);
					SET @wordID = (SELECT WordID FROM Words WHERE EnglishWord = @EnWord);
					INSERT INTO Word_Vocabulary(VocabularyID, WordID)
						VALUES (@vocabularyID, @wordID);
					RETURN 2
				END
			ELSE
				RETURN 0
		END
END

GO


