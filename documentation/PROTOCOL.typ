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
#let date = datetime.today().display()

#show: conf.with(
  document-type: document-type,
  project-name: project-name,
  team-name: team-name,
  authors: authors,
  date: date,
)

= Prelude
== Information
For the first semester of the _Computer Science and Digital Communications_
study program at _Hochschule Campus Wien — University of Applied Sciences_, a
team project is undertaken as part of the subjects _Programmierung_
(Programming) and _Teamarbeit_ (Teamwork).

This document is mainly for the Teamarbeit part. It contains at least our story
map, user stories (backlog), and mockups.

Project team members are hereinafter referred to by their
first names.

== Original Idea Description
On 2025-10-08, the following idea was pitched and ultimately decided on:
#quote(attribution: [#(
  authors.find(a => a.first-name == "Oktalon").first-name
)])[#text(style: "italic")[
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

= Design & Planning
== Story Map
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

== User Stories (Backlog)
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

  [As a],
  [User],
  [I'd like to add a price to a product in the list],
  [so that I can manage my finances.],

  [As a],
  [User],
  [I'd like to give the list a title],
  [so that I can differentiate between the lists.],

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
  [I'd like the sum to be calculated automatically],
  [so that I know how much I'll have to pay.],

  [As a],
  [User],
  [I'd like to add separation (headers) in the list],
  [so that I know which stores to visit.],

  [As a],
  [User],
  [I'd like to have checkboxes next to each entry],
  [so that I can check off stuff I've already bought.],

  [As a],
  [User],
  [I'd like to be able to export the list as a PDF],
  [so that I can print it out and bring it with me while shopping.],

  [As a],
  [User],
  [I'd like to be able to mark products as high priority],
  [so that I can focus on getting the important products in case my budget is not enough.],

  [As a],
  [User],
  [I'd like to favorite products],
  [so that I don't need to re-enter or spend time remembering it for future shopping lists.],

  [As a],
  [User],
  [I'd like to have my favorites suggested to me while typing in a product name],
  [so that I can type the shopping list faster.],
)
#set table(fill: (_, y) => {})

== Mockups
*Nemanja's mockup:*
#align(center)[
  #grid(
    columns: (auto, auto, auto),
    gutter: 1em,
    [#image(
      "Nemo_Mockup-001.png",
    )],
    [#image(
      "Nemo_Mockup-002.png",
    )],
    // [#image(
    //   "Nemo_Mockup-003.png",
    // )],
  )
]
#align(center)[
  #image(
    height: 45%,
    "Nemo_Mockup-003.png",
  )
]

*Oktalon's mockup:*
#align(center)[
  #image(
    "Talon_Mockup.drawio.png",
  )
]

= Data & Architecture
== ER Diagram
Here is a non-standards-conforming Entity Relationship (ER) Diagram which models
our data:
#image(
  "ER_Diagram-ER Diagram.drawio.png",
)

#pagebreak()

== UML Diagram
Here is a non-standards-conforming Unified Modeling Language (UML) Diagram
which also models our data:
#image(
  "ER_Diagram-UML.drawio.png",
)

#pagebreak()

== JSON
Here's how we could save data if we wanted to use JSON (JavaScript Object Model):
```json
{
  "products": [
    {
      "productID": 0,
      "name": "Milk",
      "size": "1L",
      "price": 1.99,
      "isFavorite": false
    },
    {
      "productID": 1,
      "name": "Eggs",
      "size": "10 pcs.",
      "price": 2.99,
      "isFavorite": false
    },
    {
      "productID": 2,
      "name": "Chips",
      "size": "250g",
      "price": 2.99,
      "isFavorite": true
    }
  ],
  "shoppingLists": [
    {
      "shoppingListID": 0,
      "title": "Untitled Shopping List",
      "isFavorite": false,
      "shoppingList": [
        { "type": "product", "productID": 0, "amount": 1, "priority": 0 },
        { "type": "product", "productID": 1, "amount": 2, "priority": 0 }
      ]
    },
    {
      "shoppingListID": 1,
      "title": "My Shopping List",
      "isFavorite": true,
      "shoppingList": [
        { "type": "heading", "content": "HOFER" },
        { "type": "product", "productID": 1, "amount": 1, "priority": 2 },
        { "type": "separator" }
      ]
    }
  ]
}
```