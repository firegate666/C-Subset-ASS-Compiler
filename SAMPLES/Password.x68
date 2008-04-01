*       Password.x68
*       Author : Greg Colley
*       Date   : 22/01/1999

*       Program Description :
*       This program will prompt the user to enter a password. 
*       Once entered the string must be checked against a known valid
*       Password 'PLANKTON' is there is a match then a welcome screen is
*       displayed else, the user must re-type it.

*       Definitions used in this code.

PRTSTR  EQU     0                       Print string function trap number.
READSTR EQU     2                       Read string function trap number.   

        ORG     $1000                   Start of code location.

*       Print user prompt
START   MOVEA.L #PROMPT,A1              Pointer to start of prompt text
        MOVE.B  #PRTSTR,D0              Set up print string function.
        MOVE.W  #(WELMSG-PROMPT),D1     The Prompt string length
        TRAP    #15

*       Get sentence
        MOVEA.L #USRPAS,A1                Pointer for storage for sentance
        MOVE.B  #READSTR,D0             Set up readstring function
        TRAP    #15                     Read string from KB
        MOVE.W  D1,D2                   Save Length of input string

*       Main bit
        CMPI.B  #8,D2                   Check if the user password is 8 charecters long.
        BNE     ERROR                   Beanch if not equal

*       Start checking each charecter against the correct password
        MOVEA.L #USRPAS,A0              Point to the start of the users password 
        MOVEA.L #CORPAS,A1              Point to the start of the correct password
        
COMP    CMPM.B  (A1)+,(A0)+
        BNE     ERROR
        SUBI.B  #1,D2
        BEQ     WELCOME
        JMP     COMP

WELCOME MOVEA.L #WELMSG,A1              Pointer to start of prompt text
        MOVE.B  #PRTSTR,D0              Set up print string function.
        MOVE.W  #(INVMSG-WELMSG),D1     The Prompt string length
        TRAP    #15

        STOP    #$2700                  Stop.

*       Print error message

ERROR   MOVEA.L #INVMSG,A1              Pointer to start of prompt text
        MOVE.B  #PRTSTR,D0              Set up print string function.
        MOVE.W  #(CORPAS-INVMSG),D1     The Prompt string length
        TRAP    #15
        JMP     START                   Jump back to the start

PROMPT  DC.B    'Please enter the password: '
WELMSG  DC.B    'Welcome in, your password is correct'
INVMSG  DC.B    'Your passwor is invalid please re-enter'
CORPAS  DC.B    'PLANKTON'
USRPAS  DS.B    20

        END     $1000           End of program.
