package model;

public class DeliveryDTO {

    private Long id;
    private DeliveryStatus status;

    public DeliveryDTO() {}

    public DeliveryDTO(Long id, DeliveryStatus status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
