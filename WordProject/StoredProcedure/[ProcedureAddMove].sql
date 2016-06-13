USE [Word Project]
GO

/****** Object:  StoredProcedure [dbo].[ProcedureAddMove]    Script Date: 24.04.2016 22:36:06 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO   

-- =============================================
-- Author:		<Yuriy Bereguliak>
-- =============================================
CREATE PROCEDURE [dbo].[ProcedureAddMove]
	@gameID INT,
	@userName NVARCHAR(30),
	@wordEN NVARCHAR(30),
	@wordUA NVARCHAR(30),
	@vocab NVARCHAR(30),
	@step INT
AS
DECLARE @wordID INT
DECLARE @userID INT
DECLARE @countWord INT
BEGIN
	SET @countWord = (SELECT COUNT(WordID) FROM Words WHERE EnglishWord = @wordEN AND UkrainianWord = @wordUA);
	IF (@countWord = 0)
		EXEC dbo.ProcedureAddWord @EnWord = @wordEN,  @UaWord = @wordUA, @Vocabulary = @vocab;
	SET @wordID = (SELECT WordID FROM Words WHERE EnglishWord = @wordEN AND UkrainianWord = @wordUA);
	SET @userID = (SELECT UserID FROM Users WHERE UserName = @userName);
	INSERT INTO Moves (GameID, UserID, WordID, StepCount) VALUES (@gameID, @userID, @wordID, @step);
END

GO


