*       This program will prompt you for 2 assigments and 
*       your exam mark. The program then outputs the sum 
*       of these marks like this
*       'Sum = 78'

PRTSTRCRLF      EQU     0       TRAP function to print string + CRLF
PRTSTR  EQU     1               TRAP function to print character string
NUMIN   EQU     4               TRAP function to read integer from kb
NUMOUT  EQU     3               TRAP function to o/p integer to screen

                ORG     $1000

*       Prompt for first assigment mark
        MOVEA.L #PROMPT1,A1             ;Pointer to start of prompt
        MOVE.W  #(Prompt2-Prompt1),D1   ;Set prom0pt message length
        MOVE.B  #PRTSTR,D0              ;Set up the TRAP to print string
        TRAP    #15                     ;Run it

*       Get the Assigment mark from the kb and store it
        MOVE.B  #NUMIN,D0                ;Set up trap to get number from kb
        TRAP    #15                     ;Get it
        MOVE.L  D1,ASS1                 ;Store it

*       Prompt for second assigment mark
        MOVEA.L #PROMPT2,A1             ;Pointer to start of prompt
        MOVE.W  #(Prompt3-Prompt2),D1   ;Set prom0pt message length
        MOVE.B  #PRTSTR,D0              ;Set up the TRAP to print string
        TRAP    #15                     ;Run it

*       Get the Assigment mark from the kb and store it
        MOVE.B  #NUMIN,D0                ;Set up trap to get number from kb
        TRAP    #15                     ;Get it
        MOVE.L  D1,ASS2                 ;Store it

*       Prompt for exam mark
        MOVEA.L #PROMPT3,A1             ;Pointer to start of prompt
        MOVE.W  #(Prompt4-Prompt3),D1   ;Set prom0pt message length
        MOVE.B  #PRTSTR,D0              ;Set up the TRAP to print string
        TRAP    #15                     ;Run it

*       Get the Assigment mark from the kb and store it
        MOVE.B  #NUMIN,D0               ;Set up trap to get number from kb
        TRAP    #15                     ;Get it
        MOVE.L  D1,EXAM                 ;Store it

*       Work out the Total Marks
        MOVE.L  ASS1,D2                 ;Add Assigment 1 mark to D2
        ADD.L   ASS2,D2                 ;Add Assigment 2 mark to D2
        ADD.L   EXAM,D2                 ;Add Exam mark to D2
        MOVE.L  D2,Total                ;Move D2 to total

*       Display the Total Makrs
        MOVEA.L #Prompt4,A1             ;Pointer to start o/p message
        MOVE.W  #(DUMMY-PROMPT4),D1     ;Get message length
        MOVE.B  #PRTSTR,D0              ;Set up output string trap
        TRAP    #15                     ;Print it
        MOVE.L  D2,D1
        MOVE.B  #NUMOUT,D0              ;Set up trap to o/p integer
        TRAP    #15                     ;Print it

        STOP    #$2700  


Prompt1 DC.B    'Please enter Assigment 1 mark : '
Prompt2 DC.B    'Please enter Assigment 2 mark : '
Prompt3 DC.B    'Please enter Exam mark : '
Prompt4 DC.B    'Sum = '
Dummy   DC.B    1

ASS1    DS.L    0       ;Var for Assigment 1
ASS2    DS.L    0       ;Var for Assigment 2
EXAM    DS.L    0       ;Var for Exam Mark
TOTAL   DS.L    0       ;Var for the total

        END     $1000
