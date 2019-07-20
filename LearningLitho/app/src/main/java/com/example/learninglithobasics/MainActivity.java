package com.example.learninglithobasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.Text;
import com.facebook.soloader.SoLoader;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_main);
    SoLoader.init(this, false);

    final ComponentContext context = new ComponentContext(this);

    final Component component = Text.create(context).text("Hello World").textSizeDip(50).build();
    final Component component1 =
        RecyclerCollectionComponent.create(context)
            .disablePTR(true)
            .section(ListSection.create(new SectionContext(context)).build())
            .build();

    setContentView(LithoView.create(context, component1));
  }
}
