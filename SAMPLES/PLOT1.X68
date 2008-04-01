**       Test program to check plotting graphs on the screen
*       Start with a sinewave.

POINTS  EQU      19       Number of points to plot
WRITE   EQU      6        TRAP #15 task number to write to screen
CR      EQU      $A       Carriage return
LF      EQU      $D       Linefeed

        ORG      $1000
        LEA      VALUES,A0         Pointer to table of values to be plotted
        LEA      VALUES+POINTS,A1  Pointer to end of table
        MOVE.B   #WRITE,D0         Set up TRAP to write
GET     MOVE.B   (A0)+,D7 Get next value from table
        BSR      PLOT              Plot the point and feed one line
        CMPA.L   A0,A1             Check if last point
        BNE      GET               .. if not, loop back
        STOP     #$2700            else halt.

*       Plot value in D7 on the screen. I.e. if D7 = 5, write 4 spaces
*       from left to right then an asterisk. Then feed a line.
PLOT    CMPI.B   #0,D7             Deal with zero value first
        BEQ      PPOINT   
SPACE   MOVE.B   #' ',D1           ..else send space
        TRAP     #15
        SUBQ.B   #1,D7             check if more spaces to come
        BNE      SPACE             and loop back if so
PPOINT  MOVE.B   #'*',D1           ..else send *
        TRAP     #15
        MOVE.B   #CR,D1            ..+ CR
        TRAP     #15
        MOVE.B   #LF,D1            ..+ LF
        TRAP     #15               
        RTS                        and exit
VALUES  DC.B     40,54,66,75,79,79,75,66,54
        DC.B     40,26,14,5,1,1,5,14,26,40

        END      $1000
