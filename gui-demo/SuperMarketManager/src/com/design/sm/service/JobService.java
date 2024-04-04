package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Job;

public interface JobService {

	// ͨ��ְλid��ȡְλ����
	public Object getJobName(String id) throws SQLException;

	// ����������Ʒְλ��Ϣ
	public List<Job> findAllJob() throws SQLException;

	// ����ְλ��Ϣ
	public void addJob(Job j) throws SQLException;

	// �޸�ְλ��Ϣ
	public void updateJob(Job j) throws SQLException;

	// ����idɾ��ְλ��Ϣ
	public void deleteJob(String id) throws SQLException;

	// ���ݲ��Ų�����Ӧ��ְλ��Ϣ
	public List<Job> getJobByDeptmentId(String deptId) throws SQLException;
	
	// ��List<Job>��װΪVector<Vector>����
	public Vector<Vector> pack(List<Job> list)throws SQLException;

	// ����ְλid��Ӧ�ػ�ȡ��ְλ�ľ�����Ϣ
	public Job getJobById(String id)throws SQLException;
}
