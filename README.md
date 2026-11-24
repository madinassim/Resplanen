# ✈️ Resplanen

## Om projektet
Resplanen är en Android-app som jag har skapat i kursen Androidutveckling. 
Appen hjälper användaren att planera resor och aktiviteter genom att skriva 
vad man vill göra och var.

## Funktioner
- Lägga till aktiviteter med namn och plats.
- Ändra en aktivitet.
- Ta bort en aktivitet.
- Markera aktiviteter som klara eller inte klara.
- Visa hur många aktiviteter som är klara.
- Visa ett meddelande när listan är tom.
- Scrolla genom listan när det finns många aktiviteter.
- Förhindra att tomma aktiviteter sparas.

## Teknik
Jag har använt Kotlin, Android Studio och Jetpack Compose med Material 3.

Jag har en data class som heter ResAktivitet med egenskaperna id, namn, plats och arKlar.
Jag använder en ViewModel för att hantera listan och ändringarna. 
Jag använder LazyColumn för att visa aktiviteterna i en rullande lista.

## Testning
Jag har testat att lägga till, ändra, ta bort och markera aktiviteter. 
Jag har också testat statistiken och att scrolla i listan med flera aktiviteter.

## AI-reflektion
Jag har använt ChatGPT som stöd under arbetet. 
Jag har fått hjälp att förstå Kotlin-kod, hitta fel och få förklaringar steg för steg. 
Jag har testat koden själv i Android Studio och försökt förstå hur de olika delarna fungerar.