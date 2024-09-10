package com.example.bookshelf.fake

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.Books

object FakeDataSource {
    val books = listOf<Book>(
        Book(
            accessInfo = com.example.bookshelf.model.AccessInfo(
                accessViewStatus = "accessViewStatus",
                country = "country",
                embeddable = true,
                epub = com.example.bookshelf.model.Epub(
                    acsTokenLink = "acsTokenLink",
                    downloadLink = "downloadLink",
                    isAvailable = true
                ),
                pdf = com.example.bookshelf.model.Pdf(
                    acsTokenLink = "acsTokenLink",
                    downloadLink = "downloadLink",
                    isAvailable = true
                ),
                publicDomain = true,
                quoteSharingAllowed = true,
                textToSpeechPermission = "textToSpeechPermission",
                viewability = "viewability",
                webReaderLink = "webReaderLink"
            ),
            etag = "etag",
            id = "123",
            kind = "kind",
            saleInfo = com.example.bookshelf.model.SaleInfo(
                buyLink = "buyLink",
                country = "country",
                isEbook = true,
                listPrice = com.example.bookshelf.model.ListPrice(
                    amount = 0.0,
                    currencyCode = "currencyCode"
                ),
                offers = listOf<com.example.bookshelf.model.Offer>(),
                retailPrice = com.example.bookshelf.model.RetailPriceX(
                    amount = 0.0,
                    currencyCode = "currencyCode"
                ),
                saleability = "saleability"
            ),
            searchInfo = com.example.bookshelf.model.SearchInfo(
                textSnippet = "textSnippet"
            ),
            selfLink = "selfLink",
            volumeInfo = com.example.bookshelf.model.VolumeInfo(
                allowAnonLogging = true,
                authors = listOf<String>(),
                canonicalVolumeLink = "canonicalVolumeLink",
                categories = listOf<String>(),
                contentVersion = "contentVersion",
                description = "description",
                imageLinks = com.example.bookshelf.model.ImageLinks(
                    smallThumbnail = "smallThumbnail",
                    thumbnail = "thumbnail"
                ),
                industryIdentifiers = listOf<com.example.bookshelf.model.IndustryIdentifier>(),
                infoLink = "infoLink",
                language = "language",
                maturityRating = "maturityRating",
                pageCount = 0,
                panelizationSummary = com.example.bookshelf.model.PanelizationSummary(
                    containsEpubBubbles = true,
                    containsImageBubbles = true
                ),
                previewLink = "previewLink",
                printType = "printType",
                publishedDate = "publishedDate",
                publisher = "publisher",
                readingModes = com.example.bookshelf.model.ReadingModes(
                    image = true,
                    text = true
                ),
                subtitle = "subtitle",
                title = "title"
            ),
        )
    )
}

