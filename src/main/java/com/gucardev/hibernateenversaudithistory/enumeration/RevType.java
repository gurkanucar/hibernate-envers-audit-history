package com.gucardev.hibernateenversaudithistory.enumeration;

public enum RevType {
  ADD("Added"),
  MODIFY("Modified"),
  DELETE("Deleted"),
  UNKNOWN("Unknown");

  private String label;

  RevType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }

  public static RevType fromInt(int value) {
    switch (value) {
      case 0:
        return ADD;
      case 1:
        return MODIFY;
      case 2:
        return DELETE;
      default:
        return UNKNOWN;
    }
  }
}
