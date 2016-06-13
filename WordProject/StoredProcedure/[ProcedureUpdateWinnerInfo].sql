USE [Word Project]
GO

/****** Object:  StoredProcedure [dbo].[ProcedureUpdateWinnerInfo]    Script Date: 24.04.2016 22:39:48 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[ProcedureUpdateWinnerInfo]
	@userW NVARCHAR(30),
	@round INT,
	@game INT
AS
DECLARE @countW INT
DECLARE @WuserID INT
BEGIN
	SET @countW = (SELECT UserNumberOfWins FROM Users WHERE UserName = @userW);
	UPDATE Users SET UserNumberOfWins = @countW + 1 WHERE UserName = @userW;
	SET @WuserID = (SELECT UserID FROM Users WHERE UserName = @userW);
	UPDATE Games SET WinnerUserID = @WuserID, GameNumberOfMoves = @round WHERE GameID = @game;
END

GO


