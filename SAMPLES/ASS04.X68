*       ASS04.x68
*       Author : Greg Colley
*       Date   : 13/11/198

*       Program Description :
*       This program will copy the byte contents of locations 2000, 2001
*       2002 and 2003 hex into byte locations 2001, 2002, 2003 and 2000 hex
*       without losing any data.

*       Definitions used in this code.
        
        ORG     $1000           Start of code location.

*       Main program starts here.
        
        MOVE.B  $2003,D5
        MOVE.B  $2002,$2003
        MOVE.B  $2001,$2002
        MOVE.B  $2000,$2001
        MOVE.B  D5,$2000
        
        STOP    #$2700          stop.

        END     $1000           End of program.

