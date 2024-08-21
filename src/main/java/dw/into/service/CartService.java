package dw.into.service;

import dw.into.exception.ResourceNotFoundException;
import dw.into.model.Purchase;
import dw.into.model.User;
import dw.into.repository.CartRepository;
import dw.into.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {
    CartRepository cartRepository;

    UserRepository userRepository;

    public List<Purchase> getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }


    public void removeFromCart(String userId, long cartId) {
        Optional<Purchase> cartOptional = cartRepository.findById(cartId);
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        if (cartOptional.isPresent()) {
            Purchase purchase = cartOptional.get();
            if (purchase.getUser().equals(user)) {
                cartRepository.delete(purchase);
            } else {
                throw new ResourceNotFoundException("Cart", "cartId", cartId);
            }
        } else {
            throw new ResourceNotFoundException("Cart", "cartId", cartId);
        }
    }
}

