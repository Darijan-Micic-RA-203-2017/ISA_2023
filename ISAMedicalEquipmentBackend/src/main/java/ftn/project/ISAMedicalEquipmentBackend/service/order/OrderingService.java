package ftn.project.ISAMedicalEquipmentBackend.service.order;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.google.zxing.WriterException;

import ftn.project.ISAMedicalEquipmentBackend.domain.equipment.MedicalEquipment;
import ftn.project.ISAMedicalEquipmentBackend.domain.order.EquipmentOrder;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.DetailsOfEquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.EquipmentOrderDTO;
import ftn.project.ISAMedicalEquipmentBackend.dto.order.OrderCreationDTO;
import ftn.project.ISAMedicalEquipmentBackend.exception.IncorrectSubtotalPriceOfOrderDetailsException;
import ftn.project.ISAMedicalEquipmentBackend.exception.NotEnoughEquipmentForOrderException;

public interface OrderingService {
	EquipmentOrder createOrder(OrderCreationDTO orderCreationDTO) throws 
			NotEnoughEquipmentForOrderException, IncorrectSubtotalPriceOfOrderDetailsException;
	boolean isThereEnoughEquipmentForOrder(MedicalEquipment equipment, int requestedAmountInOrder);
	boolean doesSpecifiedSubtotalPriceMatchTheExactOne(
			DetailsOfEquipmentOrderDTO detailsOfOrder, double priceOfEquipment);
	byte[] generateQRCodeOfNewEquipmentOrder(EquipmentOrderDTO newEquipmentOrder) 
			throws WriterException, IOException;
	BufferedImage generateImageOfQRCode(String barcodeText) throws WriterException;
}
