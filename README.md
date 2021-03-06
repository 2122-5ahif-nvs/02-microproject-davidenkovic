# Projekt Plattenlabel Manager
**Author:** David Ignjatovic

## Beschreibung

Der Plattenlabel Manager ist ein RestService für ein Plattenlabel.
Das Programm dient zur organisierung und zum Überblick der verschiedenen Lieder.

Man sieht welcher Künstler in dem Label unter Vertrag steht und welche Lieder er produziert/komponiert hat.
Der User hat die möglichkeit Interpretationen auf eine  Platform hochzuladen, wie z.B. Youtube, Spotify, Deezer oder auch Apple Music.

Jede Interpretation hat eine anzahl an verkauften streams die in Units gemessen wird:

* 1500 streams sind 10 song downloads.
* 10 song downloads sind 1 Album sale.
* 1 Album sale ist 1 Unit.


## Class Diagram
![cld](asciidocs/images/cld.png)

## Use Case Diagram
![ucd](asciidocs/images/ucd.png)


## GH-pages commands for windows

1. ```.\part1.sh```

2. ``docker run --rm -v ${PWD}/docs:/documents asciidoctor/docker-asciidoctor /bin/bash -c "asciidoctor -r asciidoctor-diagram -a icons=font -a experimental=true -a source-highlighter=rouge -a rouge-theme=github -a rouge-linenums-mode=inline -a docinfo=shared -a imagesdir=images -a toc=left -a toclevels=2 -a sectanchors=true -a sectnums=true -a favicon=themes/favicon.png -a sourcedir=src/main/java -b html5 '*.adoc' && rm -rf ./.asciidoctor && echo Done"``

3. ```.\part2.sh```

4. ``.\finisher.sh``
