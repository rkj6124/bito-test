import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

enum LoyaltyStatus {
    NONE,
    SILVER,
    GOLD,
    PLATINUM
}

enum ShippingMethod {
    STANDARD,
    EXPRESS,
    OVERNIGHT
}

class Customer {
    private String id;
    private String name;
    private LoyaltyStatus loyaltyStatus;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LoyaltyStatus getLoyaltyStatus() { return loyaltyStatus; }
    public void setLoyaltyStatus(LoyaltyStatus status) { this.loyaltyStatus = status; }
}

class Option {
    private String optionId;
    private String value;

    public String getOptionId() { return optionId; }
    public void setOptionId(String id) { this.optionId = id; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}

class Discount {
    private String discountId;
    private double amount;

    public String getDiscountId() { return discountId; }
    public void setDiscountId(String id) { this.discountId = id; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}

class OrderItem {
    private String productId;
    private int quantity;
    private List<Option> options;
    private List<Discount> discounts;

    public String getProductId() { return productId; }
    public void setProductId(String id) { this.productId = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int qty) { this.quantity = qty; }
    public List<Option> getOptions() { return options; }
    public void setOptions(List<Option> opts) { this.options = opts; }
    public List<Discount> getDiscounts() { return discounts; }
    public void setDiscounts(List<Discount> discs) { this.discounts = discs; }
}

class Shipping {
    private String address;
    private ShippingMethod method;
    private double cost;

    public String getAddress() { return address; }
    public void setAddress(String addr) { this.address = addr; }
    public ShippingMethod getMethod() { return method; }
    public void setMethod(ShippingMethod m) { this.method = m; }
    public double getCost() { return cost; }
    public void setCost(double c) { this.cost = c; }
}

class Coupon {
    private String code;
    private boolean valid;
    private String expiryDate;

    public String getCode() { return code; }
    public void setCode(String c) { this.code = c; }
    public boolean isValid() { return valid; }
    public void setValid(boolean v) { this.valid = v; }
    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String date) { this.expiryDate = date; }
}

class OrderInput {
    private Customer customer;
    private List<OrderItem> items;
    private Shipping shipping;
    private List<Coupon> coupons;

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer c) { this.customer = c; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> i) { this.items = i; }
    public Shipping getShipping() { return shipping; }
    public void setShipping(Shipping s) { this.shipping = s; }
    public List<Coupon> getCoupons() { return coupons; }
    public void setCoupons(List<Coupon> c) { this.coupons = c; }
}

public class ECommerce {
    public Map<String, Object> processOrder(OrderInput order) {
        Map<String, Object> finalOrder = new HashMap<>();
        if (order == null || order.getCustomer() == null || order.getCustomer().getId() == null || 
            order.getItems() == null || order.getItems().isEmpty()) {
            return null;
        }

        finalOrder.put("customerId", order.getCustomer().getId());
        finalOrder.put("customerName", order.getCustomer().getName() != null ? 
                      order.getCustomer().getName() : "Valued Customer");

        if (order.getCustomer().getLoyaltyStatus() != null && 
            order.getCustomer().getLoyaltyStatus() != LoyaltyStatus.NONE) {
            finalOrder.put("loyalty", "Status: " + order.getCustomer().getLoyaltyStatus());
        }

        Map<String, Object> items = new HashMap<>();
        finalOrder.put("items", items);

        for (int i = 0; i < order.getItems().size(); i++) {
            OrderItem item = order.getItems().get(i);
            if (item.getProductId() == null || item.getQuantity() <= 0) {
                continue;
            }

            double itemTotal = 0;
            Double productPrice = getProductPrice(item.getProductId());
            if (productPrice == null) {
                items.put("item_" + i, "Price Not Available");
                continue;
            }

            itemTotal += productPrice * item.getQuantity();

            if (item.getOptions() != null) {
                for (Option option : item.getOptions()) {
                    if (option.getOptionId() == null || option.getValue() == null) {
                        continue;
                    }
                    Double optionPrice = getOptionPrice(option.getOptionId(), option.getValue());
                    if (optionPrice != null) {
                        itemTotal += optionPrice;
                    }
                }
            }

            if (item.getDiscounts() != null) {
                for (Discount discount : item.getDiscounts()) {
                    if (discount.getDiscountId() == null || discount.getAmount() <= 0) {
                        continue;
                    }
                    itemTotal -= discount.getAmount();
                }
            }

            Map<String, Object> itemDetails = new HashMap<>();
            itemDetails.put("productId", item.getProductId());
            itemDetails.put("quantity", item.getQuantity());
            itemDetails.put("total", Math.max(itemTotal, 0));
            items.put("item_" + i, itemDetails);

            if (order.getCoupons() != null) {
                for (Coupon coupon : order.getCoupons()) {
                    if (coupon.getCode() == null || !coupon.isValid() || 
                        new Date(coupon.getExpiryDate()).before(new Date())) {
                        continue;
                    }
                    ((Map<String, Object>)items.get("item_" + i)).put("coupon", coupon.getCode());
                }
            }
        }

        if (order.getShipping() != null && order.getShipping().getMethod() != null) {
            if (order.getShipping().getCost() <= 0) {
                finalOrder.put("shipping", "Method: " + order.getShipping().getMethod() + 
                             ", Cost: Calculating");
            } else {
                finalOrder.put("shipping", "Method: " + order.getShipping().getMethod() + 
                             ", Cost: $" + order.getShipping().getCost());
            }
        } else {
            finalOrder.put("shipping", "Standard Shipping");
        }

        if (((Map<String, Object>)finalOrder.get("items")).isEmpty()) {
            return null;
        }

        return finalOrder;
    }

    private Double getProductPrice(String productId) {
        Map<String, Double> priceList = new HashMap<>();
        priceList.put("P001", 29.99);
        priceList.put("P002", 49.99);
        priceList.put("P003", 19.99);
        return priceList.get(productId);
    }

    private Double getOptionPrice(String optionId, String value) {
        Map<String, Map<String, Double>> optionPriceList = new HashMap<>();
        Map<String, Double> o001Prices = new HashMap<>();
        o001Prices.put("Red", 5.0);
        o001Prices.put("Blue", 5.0);
        Map<String, Double> o002Prices = new HashMap<>();
        o002Prices.put("Large", 10.0);
        o002Prices.put("Medium", 0.0);
        optionPriceList.put("O001", o001Prices);
        optionPriceList.put("O002", o002Prices);
        
        Map<String, Double> prices = optionPriceList.get(optionId);
        return prices != null ? prices.get(value) : null;
    }
}