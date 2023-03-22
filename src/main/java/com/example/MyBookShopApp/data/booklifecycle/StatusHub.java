package com.example.MyBookShopApp.data.booklifecycle;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class StatusHub {
    private final CartStatus cartStatus;
    private final PostponedStatus postponedStatus;
    private final BoughtStatus boughtStatus;
    private final ArchivedStatus archivedStatus;
    private final UnlinkedStatus unlinkedStatus;
    public StatusHub(CartStatus cartStatus,
                     PostponedStatus postponedStatus,
                     BoughtStatus boughtStatus,
                     ArchivedStatus archivedStatus,
                     UnlinkedStatus unlinkedStatus) {
        this.cartStatus = cartStatus;
        this.postponedStatus = postponedStatus;
        this.boughtStatus = boughtStatus;
        this.archivedStatus = archivedStatus;
        this.unlinkedStatus = unlinkedStatus;
    }

    public BookStatus getBookCurrentStatus(String slug) {
        if (isContainsSlug(cartStatus, slug)) {
            return this.cartStatus;
        } else if (isContainsSlug(postponedStatus, slug)) {
            return this.postponedStatus;
        } else if (isContainsSlug(boughtStatus, slug)) {
            return this.boughtStatus;
        } else if (isContainsSlug(archivedStatus, slug)) {
            return this.archivedStatus;
        }
        return this.unlinkedStatus;
    }
    public boolean bookIsBought(String slug) {
        return isContainsSlug(archivedStatus, slug) || isContainsSlug(boughtStatus, slug);
    }
    private boolean isContainsSlug(BookStatus bookStatus, String slug) {
        return bookStatus.getStatusCollection().stream().anyMatch(book -> book.getSlug().contains(slug));
    }

}
