package com.flipfit.dao;

import com.flipfit.bean.Slot;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SlotDAO {

    private static SlotDAO instance = null;

    // Database credentials (matching your WaitlistDAO style)
    private final String URL = "jdbc:mysql://localhost:3306/Flipfit";
    private final String USER = "root";
    private final String PASS = "Password@123";

    private SlotDAO() {}

    public static synchronized SlotDAO getInstance() {
        if (instance == null) {
            instance = new SlotDAO();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found", e);
        }
    }

    public void addSlot(Slot slot) {
        String query = "INSERT INTO slots (slotId, centerId, slotDate, startTime, totalSeats, availableSeats) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slot.getSlotId());
            stmt.setInt(2, slot.getCenterId());
            stmt.setDate(3, Date.valueOf(slot.getDate()));
            stmt.setTime(4, Time.valueOf(slot.getStartTime()));
            stmt.setInt(5, slot.getTotalSeats());
            stmt.setInt(6, slot.getSeatsAvailable());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Slot> getSlotsByCenterId(int centerId) {
        List<Slot> centerSlots = new ArrayList<>();
        String query = "SELECT * FROM slots WHERE centerId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                centerSlots.add(mapResultSetToSlot(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return centerSlots;
    }

    public Slot getSlotById(int slotId) {
        String query = "SELECT * FROM slots WHERE slotId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSlot(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Slot getSlotById(int userId, int slotId, int centerId) {
        // userId isn't typically in the slot table, but centerId check is included as requested
        String query = "SELECT * FROM slots WHERE slotId = ? AND centerId = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, slotId);
            stmt.setInt(2, centerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSlot(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Slot> getAllSlots() {
        List<Slot> slots = new ArrayList<>();
        String query = "SELECT * FROM slots";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                slots.add(mapResultSetToSlot(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slots;
    }

    public List<Slot> getAvailableSlotsByDateAndCenter(int centerId, LocalDate date) {
        List<Slot> availableSlots = new ArrayList<>();
        // Checks seats > 0 and date/time not passed
        String query = "SELECT * FROM slots WHERE centerId = ? AND slotDate = ? AND availableSeats > 0 AND (slotDate > CURRENT_DATE OR (slotDate = CURRENT_DATE AND startTime > CURRENT_TIME))";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                availableSlots.add(mapResultSetToSlot(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableSlots;
    }

    public List<Slot> getFullSlotsByDateAndCenter(int centerId, LocalDate date) {
        List<Slot> fullSlots = new ArrayList<>();
        String query = "SELECT * FROM slots WHERE centerId = ? AND slotDate = ? AND availableSeats = 0";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                fullSlots.add(mapResultSetToSlot(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fullSlots;
    }

    public List<Slot> getExpiredSlots() {
        List<Slot> expiredSlots = new ArrayList<>();
        String query = "SELECT * FROM slots WHERE slotDate < CURRENT_DATE OR (slotDate = CURRENT_DATE AND startTime < CURRENT_TIME)";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                expiredSlots.add(mapResultSetToSlot(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expiredSlots;
    }

    public List<Slot> getSlotsByDateRange(int centerId, LocalDate startDate, LocalDate endDate) {
        List<Slot> rangeSlots = new ArrayList<>();
        String query = "SELECT * FROM slots WHERE centerId = ? AND slotDate BETWEEN ? AND ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, centerId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rangeSlots.add(mapResultSetToSlot(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rangeSlots;
    }


//     Helper method to reduce code duplication
    private Slot mapResultSetToSlot(ResultSet rs) throws SQLException {
        Slot slot = new Slot();
        slot.setSlotId(rs.getInt("slotId"));
        slot.setCenterId(rs.getInt("centerId"));
        slot.setDate(rs.getDate("slotDate").toLocalDate());
        slot.setStartTime(String.valueOf(rs.getTime("startTime").toLocalTime()));
        slot.setTotalSeats(rs.getInt("totalSeats"));
        slot.setSeatsAvailable(rs.getInt("availableSeats"));
        return slot;
    }
}
