**
* PROGRAM THAT READS IN A TEXT STRING, UPPERCASES IT AND PRINTS IT OUT BACKWAR
*
* X68K, P LAMBERT, COMPUTER CENTRE, TEESSIDE POLYTECHNIC.
*
      ORG     $400
START
      MOVE.B  #1,D40
      LEA     M0,A1
      MOVEQ   #LENM0,D1
      TRAP    #15                                ;ASK WHO ARE YOU
      MOVE.B  #2,D0
      LEA     BUFF,A1
      TRAP    #15                                ;READ THE REPLY
      MOVE    D1,BLEN                            ;SAVE THE LENGTH OF THE REPLY
      BSR     UCASE                              ;CONVERT NAME TO UPPERCASE
      MOVE.B  #1,D0
      LEA     M1,A1
      MOVEQ   #LENM1,D1
      TRAP    #15                                ;PRINT "WELL "
      LEA     BUFF,A1
      MOVE    BLEN,D1
      TRAP    #15                                ;PRINT THE NAME
      LEA     M2,A1
      MOVE.B  #LENM2,D1
      TRAP    #15                                ;PRINT THE MSG
      MOVE.B  #6,D0
      LEA     BUFF,A1
      MOVE    BLEN,D2
      ADD     D2,A1
      SUBQ    #1,D1
RLOOP MOVE.B -(A1),D1                            ;PRINT NAME BACKWARDS
      TRAP    #15
      DBRA    D2,RLOOP
      MOVEQ   #1,D2
      MOVEQ   #0,D1
      MOVE.B  #0,D0
NLOOP TRAP    #15                                ;NEWLINE
      DBRA    D2,NLOOP
      STOP    #$2000

UCASE
      MOVE    BLEN,D1                            ;UPPERCASE THING IN BUFFER
      LEA     BUFF,A1
      BRA     ELOOP
LOOP  MOVE.B  (A1)+,D2
      CMP.B   #$61,D2
      BLT     ELOOP
      CMP.B   #$7A,D2
      BGT     ELOOP
      SUB.B   #$20,D2
      MOVE.B  D2,-1(A1)
ELOOP DBRA    D1,LOOP
      RTS

M0    DC.B    'WHAT IS YOUR NAME: '
LENM0 EQU     *-M0
BUFF  DS.B    80
      DS.W    0
BLEN  DS.W    1
M1    DC.B    'WELL '
LENM1 EQU     *-M1
M2    DC.B    ', YOUR NAME BACKWARDS IS: '
LENM2 EQU     *-M2
      END     $400
