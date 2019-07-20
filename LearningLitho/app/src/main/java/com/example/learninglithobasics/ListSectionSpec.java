package com.example.learninglithobasics;

import android.graphics.Color;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.Section;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.sections.common.SingleComponentSection;
import com.facebook.litho.sections.widget.ListRecyclerConfiguration;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.RenderInfo;
import static com.facebook.litho.widget.SmoothScrollAlignmentType.SNAP_TO_CENTER;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

@GroupSectionSpec
public class ListSectionSpec {

  @OnCreateChildren
  static Children onCreateChildren(final SectionContext c){
    return Children.create()
            .child(
                    SingleComponentSection.create(c)
                    .component(
                            RecyclerCollectionComponent.create(c)
                                    .disablePTR(true)
                            .recyclerConfiguration(new ListRecyclerConfiguration<>(LinearLayoutManager.HORIZONTAL, false, -6))
                            .section(
                                    DataDiffSection.<Integer>create(c)
                                    .data(generateData(32))
                                    .renderEventHandler(ListSection.onRender(c))
                                    .build()
                            )
                            .canMeasureRecycler(true)
                    ).build())
            .child(
                    DataDiffSection.<Integer>create(c)
                    .data(generateData(32))
                    .renderEventHandler(ListSection.onRender(c))
            ).build();
  }

  @OnEvent(RenderEvent.class)
  static RenderInfo onRender(final SectionContext c, @FromEvent Integer model) {
    return ComponentRenderInfo.create()
            .component(
                    ListItem.create(c)
                            .color(model % 2 == 0 ? Color.WHITE : Color.LTGRAY)
                            .title(model + ". Hello, world!")
                            .subtitle("Litho tutorial")
                            .build())
            .build();
  }

  private static List<Integer> generateData(int count) {
    //    In real scenario, this is the place to fetch data
    final List<Integer> data = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      data.add(i);
    }
    return data;
  }
}
