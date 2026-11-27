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
- Söka aktiviteter efter namn eller plats.
- Sortera aktiviteter alfabetiskt A–Ö.
- Återgå till vanlig ordning.

## Teknik
Jag har använt Kotlin, Android Studio och Jetpack Compose med Material 3.

Jag har en data class som heter ResAktivitet med egenskaperna id, namn, plats och arKlar.
Jag använder en ViewModel för att hantera listan och ändringarna. 
Jag använder LazyColumn för att visa aktiviteterna i en rullande lista.
Jag använder ResplanUiState för att samla
aktivitetslistan, söktexten och sorteringen.

ViewModel hanterar tillståndet och
uppdaterar gränssnittet när något ändras.

## Testning
Jag har testat att lägga till, ändra, ta bort och markera aktiviteter. 
Jag har också testat statistiken och att scrolla i listan med flera aktiviteter.
Jag har även testat sökfunktionen,
alfabetisk sortering och redigering
av aktiviteter när listan är sorterad.

## AI-reflektion
Jag har använt ChatGPT som stöd under arbetet. 
Jag har fått hjälp att förstå Kotlin-kod, hitta fel och få förklaringar steg för steg. 
Jag har testat koden själv i Android Studio och försökt förstå hur de olika delarna fungerar.

## Problem och lösning

Under arbetet fick jag ett problem när jag
försökte redigera en aktivitet. Appen kraschade
och Logcat visade IndexOutOfBoundsException.

Problemet berodde på att koden försökte använda
ett index som inte längre fanns i listan.

Jag löste problemet genom att kontrollera att
indexet fortfarande finns i listan innan
aktiviteten ändras.

Efter ändringen testade jag appen igen.
Då kunde jag redigera aktiviteter utan
att appen kraschade.

## Så använder man appen

1. Skriv namnet på en aktivitet.
2. Ange platsen där aktiviteten ska göras.
3. Klicka på "Lägg till aktivitet".
4. Markera checkboxen när aktiviteten är klar.
5. Klicka på "Ändra" för att redigera en aktivitet.
6. Klicka på "Ta bort" för att radera en aktivitet.

Appen visar hur många aktiviteter som är klara.
Om det finns många aktiviteter kan man scrolla
upp och ner i listan.