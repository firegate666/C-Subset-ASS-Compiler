*       Program which illustrates use of TRAPS on teh 60K simulator
*       Prompts user for his/her age and rewrite this to the screen
*       for the user's edification

*       Author : Greg
*       Date   : 08/01/99

PRTSTRCRLF      EQU     0               	      TRAP function to print string + CRLF
PRTSTR  	    EQU     1                       TRAP function to print character string
NUMIN   	    EQU     4                       TRAP function to read integer from kb
NUMOUT  	    EQU     3                       TRAP function to o/p integer to screen

        ORG     $1000
*       Prompt users for their age.
        MOVEA.L #PROMPT,A1              ;Pointer to start to prompt
        MOVE.W  #(AGE-PROMPT),D1        ;Get prompt message length
        MOVE.B  #PRTSTR,D0              ;Set up TRAP to print string
        TRAP    #15                     ;.. and print prompt

*       Get age from keyboard and store it in AGE
        MOVE.B  #NUMIN,D0               ;Set up trap to get age from kb
        TRAP    #15                     ;Get it
        MOVE.L  D1,AGE                  ;.. and store it

*       Output message
        MOVEA.L #MESSG1,A1              ;Pointer to start of o/p message
        MOVE.W  #(MESSG2-MESSG1),D1     ;Get message length
        MOVE.B  #PRTSTR,D0              ;Set up output string TRAP
        TRAP    #15                     ;.. and do it

*       Output twice user's age
        MOVE.L  AGE,D1                  ;Get user's age
        ADD.L   D1,D1                   ;Double it
        MOVE.B  #NUMOUT,D0              ;Set up TRAP to o/p integer
        TRAP    #15                     ;.. and do it

*       Finish output line     
        MOVEA.L #MESSG2,A1              ;Point to final string
        MOVE.W  #(COUNTER-MESSG2),D1    ;Get its length
        MOVE.B  #PRTSTRCRLF,D0          ;TRAP to o/p string + CRLF
        TRAP    #15                     ; and do it
        STOP    #$2700

PROMPT  DC.B    'What is your age in years? : ' 
AGE     DS.L    1                                          ;Space for age
MESSG1  DC.B    'If you were twice as old you would be '   ;Oitput message 
MESSG2  DC.B    ' years old'                               ; .. in parts
COUNTER EQU     *                                          ; End of data

        END     $1000


