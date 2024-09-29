package com.example.prac02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etStaffId, etStaffName, etBirthDate, etSalary;
    private TextView tvResult, tvStatus;
    private Button btnAddStaff;

    // Tạo danh sách nhân viên
    private ArrayList<String> staffList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần từ giao diện
        etStaffId = findViewById(R.id.etStaffId);
        etStaffName = findViewById(R.id.etStaffName);
        etBirthDate = findViewById(R.id.etBirthDate);
        etSalary = findViewById(R.id.etSalary);
        tvResult = findViewById(R.id.tvResult);
        tvStatus = findViewById(R.id.tvStatus);
        btnAddStaff = findViewById(R.id.btnAddStaff);

        // Thêm sự kiện thay đổi focus cho các EditText
        etStaffId.setOnFocusChangeListener((v, hasFocus) -> updateStatus());
        etStaffName.setOnFocusChangeListener((v, hasFocus) -> updateStatus());
        etBirthDate.setOnFocusChangeListener((v, hasFocus) -> updateStatus());
        etSalary.setOnFocusChangeListener((v, hasFocus) -> updateStatus());

        // Xử lý sự kiện khi nhấn nút "Add New Staff"
        btnAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewStaff();
            }
        });
    }

    // Hàm cập nhật trạng thái
    private void updateStatus() {
        String staffId = etStaffId.getText().toString();
        String staffName = etStaffName.getText().toString();
        String birthDate = etBirthDate.getText().toString();
        String salary = etSalary.getText().toString();

        if (staffId.isEmpty() && staffName.isEmpty() && birthDate.isEmpty() && salary.isEmpty()) {
            tvStatus.setText("Chưa nhập dữ liệu");
        } else {
            tvStatus.setText("Đã nhập nhưng chưa nhấn nút");
        }
    }

    // Hàm thêm nhân viên mới
    private void addNewStaff() {
        // Lấy thông tin từ các ô nhập liệu
        String staffId = etStaffId.getText().toString();
        String staffName = etStaffName.getText().toString();
        String birthDate = etBirthDate.getText().toString();
        String salary = etSalary.getText().toString();

        // Kiểm tra nếu thông tin bị thiếu
        if (staffId.isEmpty() || staffName.isEmpty() || birthDate.isEmpty() || salary.isEmpty()) {
            tvStatus.setText("Chưa nhập đủ dữ liệu");
            return;
        }

        // Tạo chuỗi thông tin nhân viên
        String staffInfo = staffId + "-" + staffName + "-" + birthDate + "-" + salary;

        // Thêm vào danh sách nhân viên
        staffList.add(staffInfo);

        // Hiển thị danh sách nhân viên sau khi thêm
        StringBuilder result = new StringBuilder();
        for (String staff : staffList) {
            result.append(staff).append("\n");
        }
        tvResult.setText(result.toString());

        // Xóa các trường nhập liệu sau khi thêm nhân viên
        etStaffId.setText("");
        etStaffName.setText("");
        etBirthDate.setText("");
        etSalary.setText("");

        // Cập nhật trạng thái sau khi thêm nhân viên
        if (staffList.size() == 1) {
            tvStatus.setText("Đã nhấn nút thêm mới");
        } else {
            tvStatus.setText("Sau khi thêm vài nhân viên");
        }
    }
}
