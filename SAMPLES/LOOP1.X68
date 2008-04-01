* QUESTION 1
* PROGRAM EXAMPLE USING LOOPS
* WHAT DOES THIS PROGRAM DO?
* Author : Greg Colley
* Date   : 04/12/1998

NUM1    EQU     2
NUM2    EQU     6               Loop controll Counter

        ORG     $1000

        MOVE.L #0,D0            Set the Data register to 0
        MOVE.L #NUM2,D2         Move Num2 (6) to D2

LOOP    ADD.L   #NUM1,D0        Add Num1 (2) to D0
        SUBQ.L  #1,D2           Take 1 away from D0
        BNE     LOOP            Branch if result not Zero

*RESULT IN D0

        STOP    #$2700
        END     $1000

* This program Muiltlplys Num1 (2) by Num2 (6) 
* Which will give the result 12 in D0

