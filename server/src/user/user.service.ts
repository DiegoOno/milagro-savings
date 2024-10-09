import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';

import { User } from './entities/user.entity';
import { SecurityService } from 'src/security/security.service';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(User)
    private readonly userRepository: Repository<User>,
    private readonly securityService: SecurityService,
  ) {}

  async create(user: User): Promise<User> {
    user.password = await this.securityService.hashPassword(user.password);
    return await this.userRepository.save(user);
  }

  async findAll(): Promise<User[]> {
    return await this.userRepository.find();
  }

  async findById(id: number): Promise<User> {
    return await this.userRepository.findOneBy({ id });
  }

  async findByEmail(email: string): Promise<User> {
    return await this.userRepository.findOneBy({ email });
  }

  async update(id: number, user: User): Promise<User> {
    return await this.userRepository.save({ ...user, id });
  }

  async activate(id: number): Promise<User> {
    return await this.userRepository.save({
      ...this.findById(id),
      active: true,
    });
  }

  async disable(id: number): Promise<User> {
    return await this.userRepository.save({
      ...this.findById(id),
      active: false,
    });
  }
}
