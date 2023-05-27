package pl.cholewa.sharethebills.group;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pl.cholewa.sharethebills.bill.Bill;
import pl.cholewa.sharethebills.user.User;

import java.util.List;

public record GroupResponse(
        String name
) {
}
