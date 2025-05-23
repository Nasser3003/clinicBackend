package com.clinic.demo.models.entity;

import com.clinic.demo.models.entity.user.EmployeeEntity;
import com.clinic.demo.models.entity.user.PatientEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "treatment")
public class TreatmentEntity {

    private static Map<Integer, String> treatmentsMap = new HashMap<>();
    static {
        treatmentsMap.put(1, "Braces Metal");
        treatmentsMap.put(2, "Braces Ceramic");
        treatmentsMap.put(3, "Braces Lingual");
        treatmentsMap.put(4, "Braces Invisalign");
        treatmentsMap.put(5, "Clear aligners");
        treatmentsMap.put(6, "Retainers");
        treatmentsMap.put(7, "Palatal expanders");
        treatmentsMap.put(8, "Headgear");
        treatmentsMap.put(9, "Jaw repositioning appliances");
        treatmentsMap.put(10, "Lip and cheek bumpers");
        treatmentsMap.put(11, "Removable space maintainers");
        treatmentsMap.put(12, "Splints for temporomandibular joint (TMJ) disorders");
        treatmentsMap.put(13, "Surgical orthodontics");
        treatmentsMap.put(14, "Dentofacial orthopedics");
        treatmentsMap.put(15, "Orthodontic appliances for early intervention");
        treatmentsMap.put(16, "Fixed and removable appliances for bite correction");
        treatmentsMap.put(17, "Orthognathic surgery for severe jaw discrepancies");
        treatmentsMap.put(18, "Treatment for impacted teeth");
        treatmentsMap.put(19, "Cross-bite correction");
        treatmentsMap.put(20, "Open bite correction");
        treatmentsMap.put(21, "Overbite correction");
        treatmentsMap.put(22, "Underbite correction");
        treatmentsMap.put(23, "Space closure");
        treatmentsMap.put(24, "Protraction facemask therapy");
        treatmentsMap.put(25, "Temporary anchorage devices (TADs)");
        treatmentsMap.put(26, "Distalizers for molar movement");
        treatmentsMap.put(27, "Habit appliances for thumb sucking or tongue thrusting");
        treatmentsMap.put(28, "Tongue crib appliances");
        treatmentsMap.put(29, "Cleft lip and palate orthodontic treatment");
        treatmentsMap.put(30, "Orthodontic treatment for sleep apnea");
        treatmentsMap.put(31, "Other");
    }
    public static String getTreatmentFromMap(int key) {
        return treatmentsMap.get(key);
    }

    public TreatmentEntity(EmployeeEntity doctor, PatientEntity patient, AppointmentEntity appointment, String treatment, int cost, int amountPaid, int installmentPeriodInMonths, float remainingBalance) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointment = appointment;
        this.treatment = treatment;
        this.cost = cost;
        this.amountPaid = amountPaid;
        this.installmentPeriodInMonths = installmentPeriodInMonths;
        this.remainingBalance = remainingBalance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private EmployeeEntity doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    @Column(nullable = false)
    private String treatment;

    @ManyToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private AppointmentEntity appointment;

    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime treatmentDate;

    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime lastModifiedDate;

    @Column(nullable = false)
    private int cost;

    @Column(name = "installment_period_in_months")
    private int installmentPeriodInMonths;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "remaining_balance")
    private float remainingBalance;

    private String notes;
}
