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
#let date = "2025-12-16"

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
    "StoryMap.drawio.png",
  )
]

= User Stories
User stories will be listed here...

#set table(fill: (_, y) => {
  if y == 1 { color.hsl(0deg, 100%, 50%, 10%) }
  if y >= 2 and y <= 6 { color.hsl(30deg, 100%, 50%, 10%) }
  if y >= 7 and y <= 9 { color.hsl(60deg, 100%, 50%, 10%) }
  if y >= 10 and y <= 12 { color.hsl(120deg, 100%, 50%, 10%) }
})
#table(
  columns: (auto, auto, 1fr, 1fr),
  stroke: 0.25pt + gray,
  inset: 0.75em,
  table.header([], [*Who?*], [*Do/Want What?*], [*Why?*]),
  [As a],
  [User],
  [I'd like to write a product name and add it to the list],
  [so that I can remember it while shopping.],

  [As a], [User], [I'd like to add a price to a product in the list], [so that I can manage my finances.],
  [As a], [User], [I'd like to give the list a title], [so that I can differentiate between the lists.],
  [As a],
  [User],
  [I'd like to add a size and amount to a product in the list],
  [so that I know which exact product and how many to get.],

  [As a],
  [User],
  [I'd like to be able to save and edit the list],
  [so that I can remember what I shopped for the last times and can correct mistake.],

  [As a],
  [User],
  [I'd like to have checkboxes next to each entry],
  [so that I can check off stuff I've already bought.],

  [As a], [User], [I'd like to add separation (headers) in the list], [so that I know which stores to visit.],
  [As a], [User], [I'd like the sum to be calculated automatically], [so that I know how much I'll have to pay.],
  [As a], [User], [I'd like to be able to export the list as a PDF], [so that I can print it out and bring it with me while shopping.],
  [As a], [User], [I'd like to be able to mark products as high priority], [so that I can focus on getting the important products in case my budget is not enough.],
  [As a], [User], [I'd like to favorite products], [so that I don't need to re-enter or spend time remembering it for future shopping lists.],
  [As a], [User], [I'd like to have my favorites suggested to me while typing in a product name], [so that I can type the shopping list faster.],
)
#set table(fill: (_, y) => {})

= Mockups
Mockups will be shown here...
