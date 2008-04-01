*       WORDS.X68
*       Author : Greg Colley
*       Date   : 15/01/1999

*       Program Description :
*       This program accepts an english sentence typed in after the 
*       Prompt. Works out how many words the sentence contains
*       And outputs the sentence :
*               "The Number of workds in this sentence is xx"

PRTSTR  EQU     1                       *Print string function.
READSTR EQU     2                       *Read string function.

        ORG     $1000                   *Start of code location.

* 	  Print user Prompt
START   MOVEA.L #PROMPT,A1              *Pointer to start of prompt text
        MOVE.B  #PRTSTR,D0              *Set up print string function
        MOVE.W  #(OUTPUT-PROMPT),D1     *The Prompt string lenght.
        TRAP    #15                     *Print Prompt.

* 	  Get Sentence
        MOVEA.L #WORDS,A1               *Pointer to storage for Sentance
        MOVE.B  #READSTR,D0             *Set up readstring function
        TRAP    #15                     *Get string from KB
        MOVE.W  D1,D2                   *Save Length of input string.

* 	  Count Sentence
        MOVE.L  #1,D6                   *Set character count
        MOVEA.L #WORDS,A0               *Point to start of string
NEXT    CMPI.B  #' ',(A0)+              
        BEQ     SPACE        
                       
COUNT   SUBI.B  #1,D2
        BNE     NEXT

* 	  Print out put prompt
        MOVEA.L #OUTPUT,A1              *Pointer to start of prompt text
        MOVE.B  #PRTSTR,D0              *Set up print string function
        MOVE.W  #(WORDS-OUTPUT),D1      *The prompt string lenght.
        TRAP    #15                     *Print Prompt.

* 	  Print the number of words in sentence
        MOVE.L  D6,D1                   *Move the count into the print thing.
        MOVE.B  #3,D0                   *Set up Trap 3
        TRAP    #15                     *Print Number of Words
        STOP    #$2700

SPACE   ADDI.B  #1,D6                   *Increace Count
        JMP     COUNT

PROMPT  DC.B 'Please enter your English sentence : '    *User Prompt 
OUTPUT  DC.B 'The number of words in this sentence is ' *Text for out put
WORDS   DS.B    80                                      *Thing to store the sentance (max 80)    
        
        END     $1000           End of program.
