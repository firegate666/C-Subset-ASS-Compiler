*       Program Name : Untitled
*       Author       : Greg Colley
*       Date         : 23/11/1998
*       Time         : 1:20:41 AM
*       This program echoes characters entered from the keyboard
*       Location Declarations

                       
		ORG	$7000
*		LOAD	$7000

TRMSTAT 	EQU		$10040	;TERMINAL STATUS REGISTER
TRMDATA 	EQU		$10042	;TERMINAL DATA REGISTERS
START		EQU		*
		MOVE		#START,SP
*		INPUT		 CHARACTER
INPOLL	EQU		*
		BTST		#0,TRMSTAT
		BEQ		INPOLL	;WAIT UNTIL CHARACTER AVAILABLE
		MOVE.B	TRMDATA,D3	;INPUT CHARACTER INTO D3
		CMP.B		#$0,D3
		BEQ		DONE		;DONE IF RETURN ENTERED
*		OUTPUT 	CHARACTER

OUTPOLL EQU			*
		BTST		#1,TRMSTAT
		BEQ		OUTPOLL	;WAIT FOR OUTPUT READY
		MOVE.B	D3,TRMDATA	;OUTPUT CHARACTER IN D3
		BRA		INPOLL	;GET NEXT CHARACTER
DONE		EQU		*
		JMP		$EEEEEEEE	;ILLEGAL BRANCH FOR STOP

        	STOP 		#$2700          Stop the program

       	 END     	$1000           End of assembly
