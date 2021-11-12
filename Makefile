# COMMANDES #
JAVAC = javac
# note $$ to get a single shell $
JAVAC_OPTIONS = -d build -cp build:$$CLASSPATH:"./mariadb-client.jar" -sourcepath src -implicit:none
JAVA = java
JAR = jar
EXEC_JAR = ${JAVA} -jar

# CHEMINS RELATIFS
SRC = src/fr/iutfbleau/projetIHM2021FI2
BUILD = build/fr/iutfbleau/projetIHM2021FI2
DOC = doc/fr/iutfbleau/projetIHM2021FI2

# CHOIX NOMS
JAR_MNP = test-mnp.jar
JAR_IHM1_MNP = ihm1MNP.jar
JAR_IHM1 = ihm1.jar
JAR_IHM2 = ihm2.jar

# BUTS FACTICES #
.PHONY : run clean doc

# BUT PAR DEFAUT #
run_mnp : ${JAR_MNP}
	${EXEC_JAR} ${JAR_MNP}

run_ihm1_mnp : ${JAR_IHM1_MNP}
	${EXEC_JAR_MNP} ${JAR_IHM1_MNP}

run_ihm1 : ${JAR_IHM1}
	${EXEC_JAR} ${JAR_IHM1}

run_ihm2 : ${JAR_IHM2}
	${EXEC_JAR} ${JAR_IHM2}

# AUTRE BUTS
doc :
	javadoc -d doc src/fr/iutfbleau/projetIHM2021FI2/API/*.java src/fr/iutfbleau/projetIHM2021FI2/MNP/*.java src/fr/iutfbleau/projetIHM2021FI2/MP/*.java src/fr/iutfbleau/projetIHM2021FI2/IHM1/View/*.java src/fr/iutfbleau/projetIHM2021FI2/IHM1/Model/*.java	src/fr/iutfbleau/projetIHM2021FI2/IHM1/Controller/*.java src/fr/iutfbleau/projetIHM2021FI2/IHM2/View/*.java src/fr/iutfbleau/projetIHM2021FI2/IHM2/Model/*.java	src/fr/iutfbleau/projetIHM2021FI2/IHM2/Controller/*.java

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
					 ${BUILD}/API/Chambre.class \
	  				 ${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Reservation.java

${BUILD}/API/PrereservationFactory.class : ${SRC}/API/PrereservationFactory.java \
	  					${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/PrereservationFactory.java

${BUILD}/API/ReservationFactory.class : ${SRC}/API/ReservationFactory.java \
	  					${BUILD}/API/Reservation.class 
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

${BUILD}/MNP/PrereservationFactoryNP.class : ${SRC}/MNP/PrereservationFactoryNP.java \
							  ${BUILD}/API/PrereservationFactory.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/PrereservationFactoryNP.java

${BUILD}/MNP/ReservationFactoryNP.class : ${SRC}/MNP/ReservationFactoryNP.java \
				  			  ${BUILD}/MNP/ChambreNP.class \
				  			  ${BUILD}/MNP/ReservationNP.class \
							  ${BUILD}/API/ReservationFactory.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/ReservationFactoryNP.java

## MP ##
${BUILD}/MP/ClientP.class : ${SRC}/MP/ClientP.java \
							  ${BUILD}/API/Client.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/ClientP.java

${BUILD}/MP/ChambreP.class : ${SRC}/MP/ChambreP.java \
							  ${BUILD}/API/Chambre.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/ChambreP.java

${BUILD}/MP/PrereservationP.class : ${SRC}/MP/PrereservationP.java \
						${BUILD}/MP/ClientP.class \
						${BUILD}/API/Prereservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/PrereservationP.java

${BUILD}/MP/ReservationP.class : ${SRC}/MP/ReservationP.java \
							  ${BUILD}/API/Reservation.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/ReservationP.java


${BUILD}/MP/PrereservationFactoryP.class : ${SRC}/MP/PrereservationFactoryP.java \
							${BUILD}/MP/ClientP.class\
							${BUILD}/MP/PrereservationP.class\
							${BUILD}/API/PrereservationFactory.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/PrereservationFactoryP.java

${BUILD}/MP/ReservationFactoryP.class : ${SRC}/MP/ReservationFactoryP.java \
							  ${BUILD}/MP/ClientP.class\
				  			  ${BUILD}/MP/ChambreP.class \
				  			  ${BUILD}/MP/ReservationP.class \
							  ${BUILD}/API/ReservationFactory.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MP/ReservationFactoryP.java


## TEST ##
${BUILD}/Test/TestTexteMNP.class : ${SRC}/Test/TestTexteMNP.java \
			 ${BUILD}/MNP/PrereservationNP.class \
			 ${BUILD}/MNP/PrereservationFactoryNP.class\
			 ${BUILD}/MNP/ReservationFactoryNP.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/Test/TestTexteMNP.java

#### IHM 1 ####
## Controller ##
${BUILD}/IHM1/Controller/TraitementFin.class : ${SRC}/IHM1/Controller/TraitementFin.java \
										${BUILD}/API/ReservationFactory.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementFin.java

${BUILD}/IHM1/Controller/TraitementListe.class : ${SRC}/IHM1/Controller/TraitementListe.java\
										${BUILD}/API/Chambre.class\
										${BUILD}/API/Prereservation.class\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/View/AfficherListe.class\
										${BUILD}/IHM1/Controller/TraitementFin.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementListe.java

${BUILD}/IHM1/Controller/TraitementListeValider.class : ${SRC}/IHM1/Controller/TraitementListeValider.java\
										${BUILD}/API/Chambre.class\
										${BUILD}/API/Prereservation.class\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/Model/AjoutReservation.class\
										${BUILD}/IHM1/Controller/TraitementFin.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementListeValider.java

${BUILD}/IHM1/Controller/TraitementListeValiderReference.class : ${SRC}/IHM1/Controller/TraitementListeValiderReference.java\
										${BUILD}/API/Prereservation.class\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/Model/VerificationReference.class\
										${SRC}/IHM1/View/AfficherReservations.java\
										${BUILD}/IHM1/Controller/TraitementFin.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementListeValiderReference.java

${BUILD}/IHM1/Controller/TraitementNomPrenom.class : ${SRC}/IHM1/Controller/TraitementNomPrenom.java\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/Model/VerificationNomPrenom.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementNomPrenom.java

${BUILD}/IHM1/Controller/TraitementReference.class : ${SRC}/IHM1/Controller/TraitementReference.java\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/Model/VerificationReference.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementReference.java

${BUILD}/IHM1/Controller/TraitementRetourMenu.class : ${SRC}/IHM1/Controller/TraitementRetourMenu.java\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${SRC}/IHM1/View/Menu.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementRetourMenu.java

${BUILD}/IHM1/Controller/TraitementValider.class : ${SRC}/IHM1/Controller/TraitementValider.java\
										${BUILD}/API/Chambre.class\
										${BUILD}/API/Prereservation.class\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/Model/AjoutReservation.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Controller/TraitementValider.java


## Model ##
${BUILD}/IHM1/Model/VerificationNomPrenom.class : ${SRC}/IHM1/Model/VerificationNomPrenom.java\
										${BUILD}/API/Prereservation.class\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${SRC}/IHM1/View/Menu.java\
										${BUILD}/IHM1/View/AfficherReservations.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Model/VerificationNomPrenom.java

${BUILD}/IHM1/Model/VerificationReference.class : ${SRC}/IHM1/Model/VerificationReference.java\
										${BUILD}/API/Chambre.class\
										${BUILD}/API/Prereservation.class\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${SRC}/IHM1/View/Menu.java\
										${BUILD}/IHM1/View/Afficher.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Model/VerificationReference.java

${BUILD}/IHM1/Model/AjoutReservation.class : ${SRC}/IHM1/Model/AjoutReservation.java\
										${BUILD}/API/Chambre.class\
										${BUILD}/API/Prereservation.class\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/View/Fin.class\
										${SRC}/IHM1/View/Afficher.java\
										${SRC}/IHM1/View/AfficherListe.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/Model/AjoutReservation.java

## View ##

${BUILD}/IHM1/View/Menu.class : ${SRC}/IHM1/View/Menu.java\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/Controller/TraitementReference.class\
										${BUILD}/IHM1/Controller/TraitementNomPrenom.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/View/Menu.java

${BUILD}/IHM1/View/Afficher.class : ${SRC}/IHM1/View/Afficher.java\
										${BUILD}/API/Chambre.class\
										${BUILD}/API/Prereservation.class\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/Controller/TraitementValider.class\
										${BUILD}/IHM1/Controller/TraitementListe.class\
										${BUILD}/IHM1/Controller/TraitementFin.class\
										${SRC}/IHM1/View/AfficherReservations.java	
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/View/Afficher.java

${BUILD}/IHM1/View/AfficherListe.class : ${SRC}/IHM1/View/AfficherListe.java\
										${BUILD}/API/Chambre.class\
										${BUILD}/API/Prereservation.class\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/Controller/TraitementListeValider.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/View/AfficherListe.java

${BUILD}/IHM1/View/AfficherReservations.class : ${SRC}/IHM1/View/AfficherReservations.java\
										${BUILD}/API/Prereservation.class\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/Controller/TraitementListeValiderReference.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/View/AfficherReservations.java


${BUILD}/IHM1/View/Fin.class : ${SRC}/IHM1/View/Fin.java\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM1/Controller/TraitementFin.class\
										${BUILD}/IHM1/Controller/TraitementRetourMenu.class\
										${SRC}/IHM1/View/AfficherListe.java
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/IHM1/View/Fin.java


${BUILD}/IHM1/View/Main.class : ${SRC}/IHM1/View/Main.java\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/API/Client.class\
										${BUILD}/API/TypeChambre.class\
										${BUILD}/MNP/ClientNP.class\
										${BUILD}/MNP/PrereservationFactoryNP.class\
										${BUILD}/MNP/ReservationFactoryNP.class\
										${BUILD}/IHM1/View/Menu.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM1/View/Main.java

${BUILD}/IHM1/View/MainMP.class : ${SRC}/IHM1/View/MainMP.java\
										${BUILD}/API/PrereservationFactory.class\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/API/Client.class\
										${BUILD}/API/TypeChambre.class\
										${BUILD}/MP/PrereservationFactoryP.class\
										${BUILD}/MP/ReservationFactoryP.class\
										${BUILD}/IHM1/View/Menu.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM1/View/MainMP.java

#### IHM2 ####
### Controller ###
${BUILD}/IHM2/Controller/TraitementTaux.class : ${SRC}/IHM2/Controller/TraitementTaux.java\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM2/Model/VerificationDate.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM2/Controller/TraitementTaux.java


${BUILD}/IHM2/Controller/TraitementTauxType.class : ${SRC}/IHM2/Controller/TraitementTauxType.java\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/API/TypeChambre.class\
										${BUILD}/IHM2/Model/VerificationDate.class\
										${SRC}/IHM2/View/Bureau.java
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM2/Controller/TraitementTauxType.java

${BUILD}/IHM2/Controller/TraitementGraphique.class : ${SRC}/IHM2/Controller/TraitementGraphique.java\
										${BUILD}/API/ReservationFactory.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM2/Controller/TraitementGraphique.java


### Model ###

${BUILD}/IHM2/Model/VerificationDateType.class : ${SRC}/IHM2/Model/VerificationDateType.java\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/API/TypeChambre.class\
										${BUILD}/IHM2/View/AfficherRatio.class\
										${SRC}/IHM2/View/Bureau.java
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM2/Model/VerificationDateType.java

${BUILD}/IHM2/Model/VerificationDate.class : ${SRC}/IHM2/Model/VerificationDate.java\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM2/View/AfficherRatio.class\
										${SRC}/IHM2/View/Bureau.java
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM2/Model/VerificationDate.java

${BUILD}/IHM2/Model/BD.class : ${SRC}/IHM2/Model/BD.java\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/MP/ReservationFactoryP.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM2/Model/BD.java



### View ###
${BUILD}/IHM2/View/AfficherRatio.class : ${SRC}/IHM2/View/AfficherRatio.java\
										${BUILD}/API/ReservationFactory.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM2/View/AfficherRatio.java



${BUILD}/IHM2/View/Bureau.class : ${SRC}/IHM2/View/Bureau.java\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM2/Controller/TraitementGraphqiue.class\
										${BUILD}/IHM2/Controller/TraitementTaux.class\
										${BUILD}/IHM2/Controller/TraitementTauxType.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM2/View/Bureau.java


${BUILD}/IHM2/View/Main.class : ${SRC}/IHM2/View/Main.java\
										${BUILD}/API/ReservationFactory.class\
										${BUILD}/IHM2/Model/BD.class
	${JAVAC} -Xlint:deprecation ${JAVAC_OPTIONS} ${SRC}/IHM2/View/Main.java



# ## JARS ##
 ${JAR_MNP} : ${BUILD}/Test/TestTexteMNP.class
	${JAR} cvfe ${JAR_MNP} fr.iutfbleau.projetIHM2021FI2.Test.TestTexteMNP -C build fr

${JAR_IHM1_MNP} : ${BUILD}/IHM1/View/Main.class
	${JAR} cvfe ${JAR_IHM1_MNP} fr.iutfbleau.projetIHM2021FI2.IHM1.View.Main -C build fr

${JAR_IHM1} : ${BUILD}/IHM1/View/MainMP.class
	${JAR} cvfe ${JAR_IHM1} fr.iutfbleau.projetIHM2021FI2.IHM1.View.MainMP -C build fr org

${JAR_IHM2} : ${BUILD}/IHM2/View/MainMP.class
	${JAR} cvfe ${JAR_IHM2} fr.iutfbleau.projetIHM2021FI2.IHM2.View.Main -C build fr org