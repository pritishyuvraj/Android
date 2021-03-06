package com.example.learninglithobasics;

import android.graphics.Color;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.ResType;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.RenderInfo;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;

@LayoutSpec
public class ListItemSpec {

    @OnCreateInitialState
    static void createInitialState(ComponentContext c) {
      // TODO: set an initial value for a state (optional method) https://fblitho.com/docs/state
    }

  @OnCreateLayout
  static Component onCreateLayout(
      ComponentContext c,
      @Prop int color,
      @Prop String title,
      @Prop String subtitle,
      @Prop(optional = true, resType = ResType.BOOL) boolean isCenter) {
    return Column.create(c)
        .paddingDip(YogaEdge.ALL, 16)
        .backgroundColor(color)
        .alignContent(isCenter ? YogaAlign.CENTER : null)
        .child(Text.create(c).text(title).textSizeSp(20))
        .child(Text.create(c).text(subtitle).textSizeSp(20))
        .build();
  }


}
