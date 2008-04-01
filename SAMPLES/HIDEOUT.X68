*       Program which outputs whrn run my name and address to the screen

*       Author : Greg
*       Date   : 08/01/99

        ORG     $1000

        MOVE.B  #0,D0
        MOVEA.L #NAME,A1        
        MOVE.W  #11,D1
        TRAP    #15

        MOVEA.L #ADD1,A1
        MOVE.W  #13,D1
        TRAP    #15

        MOVEA.L #ADD2,A1
        MOVE.W  #7,D1
        TRAP    #15

        STOP    #$2700

NAME    DC.B    'Greg Colley'
ADD1    DC.B    'Address Line 1'
ADD2    DC.B    'Address Line 2'

        END     $1000

