import React, { useState } from 'react';
import { Button, DatePicker } from 'antd';

function ToggleDatePickerButton() {
  const [isDatePickerVisible, setDatePickerVisible] = useState(false);

  const handleButtonClick = () => {
    setDatePickerVisible(!isDatePickerVisible); // 切换日历的显示与隐藏
  };

  const handleDatePickerChange = (date, dateString) => {
    console.log(date, dateString);
    setDatePickerVisible(false); // 选择日期后隐藏日历
  };

  const handleDatePickerCancel = () => {
    setDatePickerVisible(false);
  };

  return (
    <div>
      <Button onClick={handleButtonClick}>Calender</Button>
      {isDatePickerVisible && (
        <DatePicker
          onChange={handleDatePickerChange}
          onCancel={handleDatePickerCancel}
        />
      )}
    </div>
  );
}

export default ToggleDatePickerButton;
