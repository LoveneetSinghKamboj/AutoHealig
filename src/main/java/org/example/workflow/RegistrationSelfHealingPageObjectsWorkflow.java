package org.example.workflow;

import org.example.selfheal.RegistrationFormSelfHealing;
import org.openqa.selenium.WebDriver;

public class RegistrationSelfHealingPageObjectsWorkflow {
    private WebDriver driver;
    public RegistrationSelfHealingPageObjectsWorkflow() {
    }

    public boolean completeRegistration(String firstName) throws Exception {
        RegistrationFormSelfHealing registrationForm = new RegistrationFormSelfHealing();
        registrationForm.goToPage();
        registrationForm.enterFirstName(firstName);
        return (registrationForm.getTitle().equals("Self Healing Test Page"));
    }
}
