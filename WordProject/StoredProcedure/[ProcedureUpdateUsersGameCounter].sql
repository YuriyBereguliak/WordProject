USE [Word Project]
GO

/****** Object:  StoredProcedure [dbo].[ProcedureUpdateUsersGameCounter]    Script Date: 24.04.2016 22:39:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Yuriy Bereguliak>
-- =============================================
CREATE PROCEDURE [dbo].[ProcedureUpdateUsersGameCounter]
	@user NVARCHAR(30)
AS
DECLARE @userGames INT
DECLARE @ratio FLOAT
DECLARE @winnGames INT
BEGIN
	SET @userGames = (SELECT UserGamesCounter FROM Users WHERE UserName = @user)
	SET @winnGames = (SELECT UserNumberOfWins FROM Users WHERE UserName = @user);
	SET @userGames = @userGames + 1;
	SET @ratio = CAST(@winnGames AS FLOAT) / CAST(@userGames AS FLOAT);
	UPDATE Users SET 
		UserGamesCounter = CAST(@userGames AS INT), 
		UserPerformanceRatio = @ratio
		WHERE UserName = @user;
END

GO


