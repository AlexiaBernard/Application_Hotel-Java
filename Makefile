# COMMANDES #
JAVAC = javac
# note $$ to get a single shell $
JAVAC_OPTIONS = -d build -cp build:$$CLASSPATH -implicit:none
JAVA = java
JAR = jar
EXEC_JAR = ${JAVA} -jar

# CHEMINS RELATIFS
SRC = src/fr/iutfbleau/projetIHM2021FI2
BUILD = build/fr/iutfbleau/projetIHM2021FI2
DOC = doc/fr/iutfbleau/projetIHM2021FI2

# CHOIX NOMS
JAR_MNP = test-mnp.jar

# BUTS FACTICES #
.PHONY : run clean doc

# BUT PAR DEFAUT #
run : ${JAR_MNP}
	${EXEC_JAR} ${JAR_MNP}

# AUTRE BUTS
doc :
	javadoc -d doc src/fr/iutfbleau/projetIHM2021FI2/API/*.java src/fr/iutfbleau/projetIHM2021FI2/MNP/*.java

clean :
	rm -rf ${BUILD}/* *.jar


# REGLES DE DEPENDANCE #
## API ##
${BUILD}/API/MonPrint.class : ${SRC}/API/MonPrint.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/MonPrint.java

${BUILD}/API/TypeChambre.class : ${SRC}/API/TypeChambre.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/TypeChambre.java

${BUILD}/API/Chambre.class : ${SRC}/API/Chambre.java \
	  		     ${BUILD}/API/TypeChambre.class\
			     ${BUILD}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Chambre.java

${BUILD}/API/Client.class : ${SRC}/API/Client.java \
                            ${BUILD}/API/MonPrint.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Client.java

${BUILD}/API/Prereservation.class : ${SRC}/API/Prereservation.java \
	  		            ${BUILD}/API/TypeChambre.class \
	  		     	    ${BUILD}/API/Client.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Prereservation.java

${BUILD}/API/Reservation.class : ${SRC}/API/Reservation.java \
	  		         ${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Reservation.java

${BUILD}/API/PrereservationFactory.class : ${SRC}/API/PrereservationFactory.java \
	  		            ${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/PrereservationFactory.java

${BUILD}/API/ReservationFactory.class : ${SRC}/API/ReservationFactory.java \
	  		            ${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/ReservationFactory.java

## MNP ##
${BUILD}/MNP/ClientNP.class : ${SRC}/MNP/ClientNP.java \
                              ${BUILD}/API/Client.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/ClientNP.java

${BUILD}/MNP/ChambreNP.class : ${SRC}/MNP/ChambreNP.java \
                              ${BUILD}/API/Chambre.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/ChambreNP.java

${BUILD}/MNP/PrereservationNP.class : ${SRC}/MNP/PrereservationNP.java \
			${BUILD}/MNP/ClientNP.class \
                        ${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/PrereservationNP.java

${BUILD}/MNP/ReservationNP.class : ${SRC}/MNP/ReservationNP.java \
                              ${BUILD}/API/Reservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/ReservationNP.java

${BUILD}/MNP/PrereservationFactory.class : ${SRC}/API/PrereservationFactory.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/PrereservationFactory.java

${BUILD}/MNP/PrereservationFactoryNP.class : ${SRC}/MNP/PrereservationFactoryNP.java \
                              ${BUILD}/API/PrereservationFactory.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/PrereservationFactoryNP.java

## TEST ##
${BUILD}/Test/TestTexteMNP.class : ${SRC}/Test/TestTexteMNP.java \
			 ${BUILD}/MNP/PrereservationNP.class \
			 ${BUILD}/MNP/PrereservationFactoryNP.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/Test/TestTexteMNP.java

#### IHM 1 ####
## Controller ##
${BUILD}/IHM1/Controller/TraitementReference.class : ${SRC}/IHM1/Controller/TraitementReference.java/
										${BUILD}/IHM1/Modele/VerificationReference.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementReference.java

${BUILD}/IHM1/Controller/TraitementNomPrenom.class : ${SRC}/IHM1/Controller/TraitementNomPrenom.java/
										${BUILD}/IHM1/Modele/VerificationNomPrenom.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementNomPrenom.java

${BUILD}/IHM1/Controller/TraitementListe.class : ${SRC}/IHM1/Controller/TraitementListe.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementListe.java

${BUILD}/IHM1/Controller/TraitementValider.class : ${SRC}/IHM1/Controller/TraitementValider.java/
										${BUILD}/IHM1/Modele/AjoutReservation.class/
										${BUILD}/API/Chambre.class/
										${BUILD}/API/Prereservation.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementValider.java

## Modele ##
${BUILD}/IHM1/Modele/VerificationNomPrenom.class : ${SRC}/IHM1/Modele/VerificationNomPrenom.java/
										${BUILD}/IHM1/Vues/Menu.class/
										${BUILD}/API/Prereservation.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Modele/VerificationNomPrenom.java

${BUILD}/IHM1/Modele/VerificationReference.class : ${SRC}/IHM1/Modele/VerificationReference.java/
										${BUILD}/IHM1/Vues/Menu.class/
										${BUILD}/IHM1/Vues/Afficher.class/
										${BUILD}/API/Prereservation.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Modele/VerificationReference.java

${BUILD}/IHM1/Modele/AjoutReservation.class : ${SRC}/IHM1/Modele/AjoutReservation.java/
										${BUILD}/IHM1/Vues/Menu.class/
										${BUILD}/API/Chambre.class/
										${BUILD}/API/Prereservation.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Modele/AjoutReservation.java

## Vues ##

${BUILD}/IHM1/Vues/Menu.class : ${SRC}/IHM1/Vues/Menu.java/
										${BUILD}/IHM1/Controller/TraitementNomPrenom.class/
										${BUILD}/IHM1/Controller/TraitementReference.class										${BUILD}/API/Prereservation.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Vues/Menu.java

${BUILD}/IHM1/Vues/Afficher.class : ${SRC}/IHM1/Vues/Afficher.java/
										${BUILD}/IHM1/Controller/TraitementValider.class/
										${BUILD}/IHM1/Controller/TraitementListe.class/
										${BUILD}/API/Chambre.class/
										${BUILD}/API/Prereservation.class									${BUILD}/API/Prereservation.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Vues/Afficher.java

${BUILD}/IHM1/Vues/Main.class : ${SRC}/IHM1/Vues/Main.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Vues/Main.java


# ## JARS ##
 ${JAR_MNP} : ${BUILD}/Test/TestTexteMNP.class
	${JAR} cvfe ${JAR_MNP} fr.iutfbleau.projetIHM2021FI2.Test.TestTexteMNP -C build fr