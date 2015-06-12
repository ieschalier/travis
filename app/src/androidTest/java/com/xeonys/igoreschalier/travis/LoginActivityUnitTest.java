package com.xeonys.igoreschalier.travis;

import android.content.Intent;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Created by igoreschalier on 12/06/15.
 */
public class LoginActivityUnitTest extends
        android.test.ActivityUnitTestCase<LoginActivity>  {

    private LoginActivity activity;

    public LoginActivityUnitTest(Class<LoginActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                LoginActivity.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    public LoginActivityUnitTest() {
        super(LoginActivity.class);
    }

    // Sanity check for the layout
    @SmallTest
    public void testLayoutExists() {
        // Verifies the button and text field exist
        assertNotNull(activity.findViewById(R.id.email_sign_in_button));
        assertNotNull(activity.findViewById(R.id.email));
        assertNotNull(activity.findViewById(R.id.password));

        // Verifies the text of the button
        Button view = (Button) activity.findViewById(R.id.email_sign_in_button);
        assertEquals("Incorrect label of the button", "Sign in or register", view.getText());
    }

    @SmallTest
    public void testSomething() {
        // assertions here
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    // Validate the intent is fired on button press with correct result from
    // text field
    @SmallTest
    public void testIntentTriggerViaOnClick() {
        String fieldValue = "Testing Text";
        // Set a value into the text field
        final EditText etResult = (EditText) activity.findViewById(R.id.email);
        /*getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                etResult.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync(fieldValue);*/
        etResult.setText(fieldValue);
        // Verify button exists on screen
        Button btnLaunch = (Button) activity.findViewById(R.id.email_sign_in_button);
        assertNotNull("Button should not be null", btnLaunch);
        // Trigger a click on the button
        btnLaunch.performClick();
        // Verify the intent was started with correct result extra
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent should have triggered after button press",
                triggeredIntent);
        String data = triggeredIntent.getExtras().getString("result");
        assertEquals("Incorrect result data passed via the intent",
                "Testing Text", data);
    }
}
