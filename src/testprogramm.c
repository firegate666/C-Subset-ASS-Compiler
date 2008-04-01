testprogramm {

	int zahl1=0;
	int zahl2=0;
	int ergebnis = 0;
	int eingabe=0;
	int testzahl=5;

	printf("Testzahl: ",testzahl);
	printf("\n");

	printf("Geben Sie eine Zahl ein: ");
	scanf(eingabe);

	if(eingabe>0) {
		printf("Die eingegebene Zahl ist positiv.\n");
	} else {
	   if (eingabe == 0) {
	    printf("Null");
	    }
	   else {
		printf("Die eingegebene Zahl ist kleiner oder gleich 0.\n");
	   }
	};

	zahl1 = 3;
	zahl2 = 2;

	ergebnis = (zahl1 - zahl2)*eingabe/2;
	printf("(3-2)*eingabe/2= ");
	printf(ergebnis);
	printf("\n");

	do {
		zahl2 = 2;
		while (zahl2>0) {
			printf("Durchlauf innere Schleife.\n");
			zahl2 = zahl2 -1;
		};
		printf("Durchlauf �u�ere Schleife.\n");
		zahl1 = zahl1 -1;
	} while(zahl1 > 0);
}
