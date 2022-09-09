FROM java:8
ADD build/classes/java/main/com/choinhet/pokedexinteraction/*.jar dockerapp.jar
EXPOSE 8080
CMD ["java", "-jar", "dockerapp.jar"]