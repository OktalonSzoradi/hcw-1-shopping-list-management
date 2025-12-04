#import "conf.typ": *

#let document-type = "Protocol"
#let project-name = "Shopping List Manager"
#let team-name = "BONK"
#let authors = (
  (
    first-name: "Oktalon",
    middle-names: ("Andik",),
    last-name: "Szórádi",
    nick-name: "Talon",
  ),
  (
    first-name: "Nemanja",
    middle-names: (),
    last-name: "Markovic",
    nick-name: "Nemo",
  ),
  (
    first-name: "Karl",
    middle-names: ("Nikolaus",),
    last-name: "Müllner",
    nick-name: "Karli",
  ),
)
#let date = "2025-12-04"

#show: conf.with(
  document-type: document-type,
  project-name: project-name,
  team-name: team-name,
  authors: authors,
  date: date,
)

= Information
For the first semester of the _Computer Science and Digital Communications_
study program at _Hochschule Campus Wien — University of Applied Sciences_, a
team project is undertaken as part of the subjects _Programmierung_
(Programming) and _Teamarbeit_ (Teamwork).

This document is mainly for the Teamarbeit part. It contains at least our story
map, user stories, and mockups.

Project team members are hereinafter referred to by their
first names.

= Original Idea Description
On 2025-10-08, the following idea was pitched and ultimately decided on:
#quote(attribution: [#authors.find(a => a.first-name == "Oktalon").first-name])[#text(style: "italic")[
  Eine mögliche Idee:\
  Einkaufslistenverwaltung

  Man kann Einkaufslisten erstellen, speichern, bearbeiten, löschen, und als
  Favorit markieren.\
  Ein Einkaufslisteneintrag hat Name, Größe, Menge, und Preis. Dies ohne Menge
  kann man auch als Favorit markieren.\
  Die Summe wird dann automatisch berechnet.

  Und es besteht die Möglichkeit es auszudrucken. Dabei wird die Typst CLI
  verwendet.
]]
English translation:
#quote(attribution: [#authors.find(a => a.first-name == "Oktalon").first-name])[
  One possible idea:\
  Shopping List Management

  You can create, save, edit, delete, and favorite shopping lists.\
  A shopping list entry has a name, size, amount, and price. This, without the
  amount, can be favorited.\
  The sum is automatically calculated

  Also, it should be possible to print it out. The Typst CLI will be used for
  this.
]

#pagebreak()

= Story Map
We made this together on 2025-11-27:
#align(center)[
  #image(
    height: 45%,
    "WhatsApp Image 2025-11-27 at 13.41.55.jpeg",
  )
]
Digital version:
#align(left)[
  #image(
    width: 115%,
    "StoryMap.drawio.png",
  )
]

= User Stories
User stories will be listed here...

= Mockups
Mockups will be shown here...
