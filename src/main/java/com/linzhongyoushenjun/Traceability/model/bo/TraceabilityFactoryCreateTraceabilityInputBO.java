package com.linzhongyoushenjun.Traceability.model.bo;

import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraceabilityFactoryCreateTraceabilityInputBO {
  private byte[] goodsGroup;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(goodsGroup);
    return args;
  }
}
