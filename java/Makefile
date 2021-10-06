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

# CHOIX NOMS
JAR_MNP = test-mnp.jar

# BUTS FACTICES #
.PHONY : run clean

# BUT PAR DEFAUT #
run : ${BUILD}/MNP/JoueurNP.class

# ${JAR_MNP}
# 	${EXEC_JAR} ${JAR_MNP}

# AUTRE BUTS
clean :
	rm -rf ${BUILD}/* *.jar


# REGLES DE DEPENDANCE #

## API ##
${BUILD}/API/TypeChambre.class : ${SRC}/API/TypeChambre.java 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/TypeChambre.java

${BUILD}/API/Chambre.class : ${SRC}/API/Chambre.java \
	  		     ${BUILD}/API/TypeChambre.class
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/API/Chambre.java

${BUILD}/API/Client.class : ${SRC}/API/Client.java 
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

${BUILD}/MNP/JoueurNP.class : ${SRC}/MNP/ClientNP.java \
                              ${BUILD}/API/Client.class 
	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/MNP/ClientNP.java


## TEST ##

# ${BUILD}/test/TestTexteMNP.class : ${SRC}/test/TestTexteMNP.java \
#                                    ${BUILD}/MNP/PassagePieceFactoryNP.class \
#                                    ${BUILD}/MNP/TrucFactoryNP.class \
#                                    ${BUILD}/MNP/JoueurNP.class
# 	${JAVAC} ${JAVAC_OPTIONS} ${SRC}/test/TestTexteMNP.java


# ## JARS ##

# ${JAR_MNP} : ${BUILD}/test/TestTexteMNP.class
# 	${JAR} cvfe ${JAR_MNP} fr.iutfbleau.projetIHM2020FI2.test.TestTexteMNP -C build fr

