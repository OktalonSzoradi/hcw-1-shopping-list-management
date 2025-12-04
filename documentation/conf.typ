#let show-authors(
  authors: none,
  type: none,
) = {
  if (type == "first-page") {
    grid(
      columns: (authors.len()) * (1fr,),
      align: center,
      // for (author) in (authors) {
      //   [#upper(author.last-name), #author.first-name #author.middle-names.join()]
      // },
      ..authors.map(author => {
        [#upper(author.last-name), #author.first-name #author.middle-names.join()]
      })
    )
  } else if (type == "first-page-2") {
    authors.map(author => {
      [#upper(author.last-name), #author.first-name #author.middle-names.join()]
    })
  } else {
    authors.map(author => { author.first-name }).join(", ")
  }
}

#let conf(
  document-type: none,
  project-name: none,
  team-name: none,
  authors: none,
  date: none,
  doc,
) = {
  let stroke-gap = 4pt
  let stroke-thickness = 0.25pt
  let light-text = color.luma(100)
  set text(
    // font: "New Computer Modern",
    size: 12pt,
  )
  show raw: set text(font: "Fira Code")
  set par(
    justify: true,
  )
  set page(
    header: context {
      set text(fill: light-text)
      set par(justify: false)
      if (counter(page).get().at(0) != 1) [
        #grid(
          columns: (1fr, auto, 1fr),
          align: (left, center, right),
          inset: (bottom: stroke-gap),
          stroke: (bottom: (paint: light-text, thickness: stroke-thickness)),
          [#project-name], [], [#text(style: "italic")[#team-name]],
        )
      ]
    },
    footer: context {
      set text(fill: light-text)
      if (counter(page).get().at(0) != 1) [
        #grid(
          columns: (1fr, auto, 1fr),
          align: (left, center, right),
          inset: (top: stroke-gap),
          stroke: (top: (paint: light-text, thickness: stroke-thickness)),
          [_#show-authors(authors: authors)_],
          [],
          [Page
            #counter(page).display(
              "1 of 1",
              both: true,
            )],
        )
      ]
    },
  )
  set line(
    length: 100%,
    stroke: 0.5pt,
  )
  set quote(block: true)
  // set heading(
  //   // numbering: (n1, ..x) => numbering("1.a.", calc.max(0, n1 - 1), ..x),
  //   // numbering: (..x) => numbering("1.", ..x.pos().map(n => calc.max(0, n - 1)))
  // )
  show heading: set block(above: 4em, below: 1em)
  show link: set text(fill: blue)
  show link: underline

  v(3.5cm)
  align(center)[
    #text(
      size: 1.5em,
    )[#document-type]
    #v(-1em)
    #text(
      size: 2.5em,
    )[#project-name]
  ]
  v(0.5cm)
  align(center)[
    #text[Team *#team-name*:]
    #show-authors(authors: authors, type: "first-page")
  ]
  v(2cm)
  text[Last edited: #date]
  v(1cm)
  // table(
  //   columns: (auto, 1fr),
  //   stroke: none,
  //   [Team name:], [#team-name],
  //   [Authors:], [#show-authors(authors: authors, type: "first-page-2").join("\n")],
  //   [],[],
  //   [Last edited:], [#date]
  // )
  outline(title: "Table of Contents")
  pagebreak()
  doc
}

// Based off
// https://github.com/angelonazzaro/typxidian/blob/main/src/lib.typ#L79
#let comment(
  body,
  language: "de",
  inset: (top: 0.75em, bottom: 1em, right: 0.75em, left: 0.75em),
  width: 100%,
  height: auto,
  fill: color.hsl(0deg, 0%, 80%, 33%),
  text-style: "italic",
) = {
  set text(lang: language)
  figure(
    caption: none,
    align(left, box(
      radius: 0.25em,
      inset: inset,
      width: width,
      height: height,
      fill: fill,
      clip: true,
      stroke: (left: 2.5pt + fill.opacify(100%).darken(50%)),
      [
        #text(style: text-style)[#body]
      ],
    )),
  )
  set text(lang: "en")
}
