*	Programmstart
	ORG     $1000
*	PRINTANWEISUNG
	MOVEA.L #STRVAR0,A1
	MOVE.W #(STRVAR1-STRVAR0),D1
	MOVE.B #PRTSTR,D0
	TRAP #15
*	SCANANWEISUNG
	MOVE.B #NUMIN,D0
	TRAP #15
	MOVE.W D1,eingabe
	MOVE.W eingabe,D0
	CMP.W #0,D0
	BLE EB_0
*	PRINTANWEISUNG
	MOVEA.L #STRVAR1,A1
	MOVE.W #(STRVAR2-STRVAR1),D1
	MOVE.B #PRTSTRCLRLF,D0
	TRAP #15
	JMP EE_1
EB_0
	MOVE.W eingabe,D0
	CMP.W #0,D0
	BNE EB_2
*	PRINTANWEISUNG
	MOVEA.L #STRVAR2,A1
	MOVE.W #(STRVAR3-STRVAR2),D1
	MOVE.B #PRTSTRCLRLF,D0
	TRAP #15
	JMP EE_3
EB_2
*	PRINTANWEISUNG
	MOVEA.L #STRVAR3,A1
	MOVE.W #(STRVAR4-STRVAR3),D1
	MOVE.B #PRTSTRCLRLF,D0
	TRAP #15
EE_3
EE_1
	MOVE.W #3,zahl1
	MOVE.W #2,zahl2
	MOVE.W zahl1,D0
	SUB.W zahl2,D0
	MULS eingabe,D0
	DIVS #2,D0
	MOVE.W D0,ergebnis
*	PRINTANWEISUNG
	MOVEA.L #STRVAR4,A1
	MOVE.W #(STRVAR5-STRVAR4),D1
	MOVE.B #PRTSTR,D0
	TRAP #15
*	PRINTANWEISUNG
	MOVE.W ergebnis,D1
	MOVE.B #NUMOUT,D0
	TRAP #15
*	PRINTANWEISUNG
	MOVEA.L #STRVAR5,A1
	MOVE.W #(STRVAR6-STRVAR5),D1
	MOVE.B #PRTSTRCLRLF,D0
	TRAP #15
DWB_4
	MOVE.W #2,zahl2
WB_6
	MOVE.W zahl2,D0
	CMP.W #0,D0
	BLE WE_7
*	PRINTANWEISUNG
	MOVEA.L #STRVAR6,A1
	MOVE.W #(STRVAR7-STRVAR6),D1
	MOVE.B #PRTSTRCLRLF,D0
	TRAP #15
	MOVE.W zahl2,D0
	SUB.W #1,D0
	MOVE.W D0,zahl2
	JMP WB_6
WE_7
*	PRINTANWEISUNG
	MOVEA.L #STRVAR7,A1
	MOVE.W #(STRVAR8-STRVAR7),D1
	MOVE.B #PRTSTRCLRLF,D0
	TRAP #15
	MOVE.W zahl1,D0
	SUB.W #1,D0
	MOVE.W D0,zahl1
	MOVE.W zahl1,D0
	CMP.W #0,D0
	BLE DWE_5
	JMP DWB_4
DWE_5
	STOP	#$2700
*	BEGIN PRINTF-DECLARE
PRTSTRCLRLF	EQU	0
PRTSTR	EQU	1
NUMIN	EQU	4
NUMOUT	EQU	3
*	END PRINTF_DECLARE
*	BEGIN VAR-DECLARE
zahl1	DC.W	0
zahl2	DC.W	0
ergebnis	DC.W	0
eingabe	DC.W	0
STRVAR0	DC.B	'Geben Sie eine Zahl ein: '
STRVAR1	DC.B	'Die eingegebene Zahl ist positiv.'
STRVAR2	DC.B	'Null'
STRVAR3	DC.B	'Die eingegebene Zahl ist kleiner oder gleich 0.'
STRVAR4	DC.B	'(3-2)*eingabe/2= '
STRVAR5	DC.B	' '
STRVAR6	DC.B	'Durchlauf innere Schleife.'
STRVAR7	DC.B	'Durchlauf �u�ere Schleife.'
STRVAR8	DC.B	'*'
*	END VAR-DECLARE
	END	$1000